package com.esqueleto.esqueletosdk.command;

import android.content.Context;

import java.io.InputStream;

/**
 * Created by Raúl on 14/08/2014.
 */
public interface ImportCommand {

    void execute();
    void undo();

}
