package io.quarkiverse.jdbc.sqlite.deployment;

import io.quarkiverse.jdbc.sqlite.runtime.SQLiteAgroalConnectionConfigurer;
import io.quarkus.agroal.spi.JdbcDriverBuildItem;
import io.quarkus.arc.deployment.AdditionalBeanBuildItem;
import io.quarkus.arc.processor.DotNames;
import io.quarkus.datasource.deployment.spi.DefaultDataSourceDbKindBuildItem;
import io.quarkus.deployment.Capabilities;
import io.quarkus.deployment.Capability;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.deployment.builditem.SslNativeConfigBuildItem;

public class JDBCSQLiteProcessor {
    private static final String SQLITE = "sqlite";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem("jdbc-sqlite");
    }

    @BuildStep
    JdbcDriverBuildItem registerDriver(SslNativeConfigBuildItem sslNativeConfig) {
        return new JdbcDriverBuildItem(SQLITE, "org.sqlite.JDBC");
    }

    @BuildStep
    void configureAgroalConnection(Capabilities capabilities,
            BuildProducer<AdditionalBeanBuildItem> additionalBeans) {
        if (capabilities.isMissing(Capability.AGROAL)) {
            return;
        }
        additionalBeans.produce(AdditionalBeanBuildItem.builder().addBeanClass(SQLiteAgroalConnectionConfigurer.class)
                .setDefaultScope(DotNames.APPLICATION_SCOPED)
                .setUnremovable()
                .build());
    }

    @BuildStep
    DefaultDataSourceDbKindBuildItem registerDbKind() {
        return new DefaultDataSourceDbKindBuildItem(SQLITE);
    }
}
