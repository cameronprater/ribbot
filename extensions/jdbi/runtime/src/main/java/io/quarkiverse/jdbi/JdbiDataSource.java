package io.quarkiverse.jdbi;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Qualifier;

@Target({ TYPE, FIELD, METHOD, PARAMETER })
@Retention(RUNTIME)
@Documented
@Qualifier
public @interface JdbiDataSource {

    String value();

    class JdbiDataSourceLiteral extends AnnotationLiteral<JdbiDataSource> implements JdbiDataSource {
        private final String value;

        private JdbiDataSourceLiteral(String value) {
            this.value = value;
        }

        public static JdbiDataSourceLiteral of(String value) {
            return new JdbiDataSourceLiteral(value);
        }

        @Override
        public String value() {
            return value;
        }
    }
}
