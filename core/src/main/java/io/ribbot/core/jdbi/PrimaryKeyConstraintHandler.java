package io.ribbot.core.jdbi;

import static org.sqlite.SQLiteErrorCode.SQLITE_CONSTRAINT_PRIMARYKEY;

import org.jdbi.v3.core.statement.UnableToExecuteStatementException;
import org.sqlite.SQLiteException;

public class PrimaryKeyConstraintHandler {

    public static void handleInsert(Runnable insertRunnable) {
        try {
            insertRunnable.run();
        } catch (UnableToExecuteStatementException e) {
            Throwable cause = e.getCause();
            if (!(cause instanceof SQLiteException)
                    || ((SQLiteException) cause).getResultCode() != SQLITE_CONSTRAINT_PRIMARYKEY) {
                throw e;
            }
        }
    }

    private PrimaryKeyConstraintHandler() {
    }
}
