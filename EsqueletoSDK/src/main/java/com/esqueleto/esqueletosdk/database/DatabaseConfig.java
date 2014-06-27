package com.esqueleto.esqueletosdk.database;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by RUL on 26/06/2014.
 */
public class DatabaseConfig extends OrmLiteConfigUtil{

    public static void main(String[] args) throws IOException, SQLException {
        writeConfigFile("ormlite_config.txt");
    }

}
