package com.esqueleto.esqueletosdk.command;

import android.content.Context;

/**
 * Created by rgonzalez on 24/04/2014.
 */
public interface DeleteCommand {

    void execute(Context ctx);
    void undo();
}

