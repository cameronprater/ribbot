package io.quarkiverse.jdbi.deployment;

import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.jboss.jandex.*;
import org.jdbi.v3.core.Jdbi;

import io.quarkiverse.jdbi.JdbiDataSource;
import io.quarkiverse.jdbi.runtime.JdbiRecorder;
import io.quarkus.arc.deployment.AdditionalBeanBuildItem;
import io.quarkus.arc.deployment.BeanRegistrationPhaseBuildItem;
import io.quarkus.arc.deployment.SyntheticBeanBuildItem;
import io.quarkus.arc.deployment.SyntheticBeanBuildItem.ExtendedBeanConfigurator;
import io.quarkus.arc.processor.DotNames;
import io.quarkus.arc.processor.InjectionPointInfo;
import io.quarkus.datasource.common.runtime.DataSourceUtil;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.annotations.ExecutionTime;
import io.quarkus.deployment.annotations.Record;
import io.quarkus.deployment.builditem.CombinedIndexBuildItem;
import io.quarkus.deployment.builditem.FeatureBuildItem;

public class JdbiProcessor {
    private static final DotName JDBI = DotName.createSimple(Jdbi.class.getName());
    private static final DotName JDBI_DATA_SOURCE = DotName.createSimple(JdbiDataSource.class.getName());

    @Inject
    CombinedIndexBuildItem combinedIndex;

    private SyntheticBeanBuildItem syntheticBean(JdbiRecorder recorder,
            ExtendedBeanConfigurator beanConfigurator,
            String name) {
        if (DataSourceUtil.isDefault(name)) {
            beanConfigurator.addQualifier(DotNames.DEFAULT);
        } else {
            beanConfigurator.addQualifier().annotation(JDBI_DATA_SOURCE).addValue("value", name).done();
            beanConfigurator.addQualifier().annotation(DotNames.NAMED).addValue("value", name).done();
        }
        return beanConfigurator.supplier(recorder.jdbi(name)).done();
    }

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem("jdbi");
    }

    @BuildStep
    AdditionalBeanBuildItem additionalBeans() {
        return new AdditionalBeanBuildItem(JdbiDataSource.class);
    }

    @BuildStep
    @Record(ExecutionTime.RUNTIME_INIT)
    void syntheticBeans(JdbiRecorder recorder,
            BeanRegistrationPhaseBuildItem registrationPhase,
            BuildProducer<SyntheticBeanBuildItem> syntheticBeans) {
        Set<String> names = new HashSet<>();
        boolean defaultProduced = false;

        for (InjectionPointInfo injectionPoint : registrationPhase.getInjectionPoints()) {
            DotName injectionPointType = injectionPoint.getRequiredType().name();

            if (injectionPointType.equals(JDBI)) {
                ExtendedBeanConfigurator beanConfigurator = SyntheticBeanBuildItem.configure(Jdbi.class)
                        .scope(ApplicationScoped.class)
                        .setRuntimeInit()
                        .unremovable();
                AnnotationInstance jdbiDataSource = injectionPoint.getRequiredQualifier(JDBI_DATA_SOURCE);
                AnnotationInstance named = injectionPoint.getRequiredQualifier(DotNames.NAMED);

                if (!defaultProduced && injectionPoint.hasDefaultedQualifier()) {
                    syntheticBeans.produce(syntheticBean(recorder, beanConfigurator, DataSourceUtil.DEFAULT_DATASOURCE_NAME));
                    defaultProduced = true;
                } else if (jdbiDataSource != null || named != null) {
                    String name = (jdbiDataSource != null ? jdbiDataSource.value() : named.value()).asString();
                    if (!names.contains(name)) {
                        syntheticBeans.produce(syntheticBean(recorder, beanConfigurator, name));
                        names.add(name);
                    }
                }
            }
        }
    }
}
