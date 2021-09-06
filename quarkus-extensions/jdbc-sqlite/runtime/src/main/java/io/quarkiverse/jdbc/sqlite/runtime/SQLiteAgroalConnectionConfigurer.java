package io.quarkiverse.jdbc.sqlite.runtime;

import io.agroal.api.configuration.supplier.AgroalDataSourceConfigurationSupplier;
import io.quarkus.agroal.runtime.AgroalConnectionConfigurer;
import io.quarkus.agroal.runtime.JdbcDriver;

@JdbcDriver("sqlite")
public class SQLiteAgroalConnectionConfigurer implements AgroalConnectionConfigurer {

    @Override
    public void setExceptionSorter(String dbKind, AgroalDataSourceConfigurationSupplier dataSourceConfiguration) {
    }
}
