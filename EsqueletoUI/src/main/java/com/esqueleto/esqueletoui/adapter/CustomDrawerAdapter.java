package com.esqueleto.esqueletoui.adapter;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.esqueleto.esqueletosdk.command.impl.UpdateCuenta;
import com.esqueleto.esqueletosdk.iteractor.impl.GestorCuenta;
import com.esqueleto.esqueletosdk.model.Cuenta;
import com.esqueleto.esqueletoui.R;
import com.esqueleto.esqueletoui.adapter.item.DrawerItem;
import com.esqueleto.esqueletoui.adapter.item.SpinnerItem;
import com.esqueleto.esqueletoui.ui.fragment.form.ResumenFragment;

import java.util.List;

/**
 * Created by rgonzalez on 14/07/2014.
 */
public class CustomDrawerAdapter extends ArrayAdapter<DrawerItem> {

    Context context;
    List<DrawerItem> drawerItemList;
    int layoutResID;
    List<SpinnerItem> cuentasList;

    public CustomDrawerAdapter(Context context, int layoutResourceID,
                               List<DrawerItem> listItems, List<SpinnerItem> cuentasList) {
        super(context, layoutResourceID, listItems);
        this.context = context;
        this.drawerItemList = listItems;
        this.layoutResID = layoutResourceID;
        this.cuentasList = cuentasList;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        DrawerItemHolder drawerHolder;
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            drawerHolder = new DrawerItemHolder();

            view = inflater.inflate(layoutResID, parent, false);
            drawerHolder.ItemName = (TextView) view
                    .findViewById(R.id.drawer_itemName);
            drawerHolder.icon = (ImageView) view.findViewById(R.id.drawer_icon);

            drawerHolder.spinner = (Spinner) view
                    .findViewById(R.id.drawerSpinner);

            drawerHolder.title = (TextView) view.findViewById(R.id.drawerTitle);

            drawerHolder.headerLayout = (LinearLayout) view
                    .findViewById(R.id.headerLayout);
            drawerHolder.itemLayout = (LinearLayout) view
                    .findViewById(R.id.itemLayout);
            drawerHolder.spinnerLayout = (LinearLayout) view
                    .findViewById(R.id.spinnerLayout);

            view.setTag(drawerHolder);

        } else {
            drawerHolder = (DrawerItemHolder) view.getTag();

        }

        DrawerItem dItem = (DrawerItem) this.drawerItemList.get(position);

        if (dItem.isSpinner()) {
            drawerHolder.headerLayout.setVisibility(LinearLayout.INVISIBLE);
            drawerHolder.itemLayout.setVisibility(LinearLayout.INVISIBLE);
            drawerHolder.spinnerLayout.setVisibility(LinearLayout.VISIBLE);

            CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(context,
                    R.layout.custom_spinner_item, cuentasList);

            drawerHolder.spinner.setAdapter(adapter);

            drawerHolder.spinner
                    .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                        @Override
                        public void onItemSelected(AdapterView<?> arg0,
                                                   View arg1, int arg2, long arg3) {
                            Toast.makeText(context, "User Changed",
                                    Toast.LENGTH_SHORT).show();

//                            TextView tv = (TextView) arg1.findViewById(R.id.text_main_name);
//                            Bundle arguments = new Bundle();
//                            //TODO: El id de la cuenta lo obtendremos del spinner del navigation drawer
//                            Cuenta cuenta = (Cuenta)tv.getTag();
//                            arguments.putParcelable("cuenta", cuenta);
//                            // Crear un nuevo fragmento y transacción
//                            ResumenFragment newFragment2 = ResumenFragment.newInstance(arguments);
//                            FragmentTransaction transaction2 = ((Activity) context).getFragmentManager().beginTransaction();
//                            StringBuffer title = new StringBuffer(cuenta.getNombre());
//
//                            loadFragment(newFragment2, ResumenFragment.TAG, title.toString());
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> arg0) {
                            // TODO Auto-generated method stub
                            System.out.println("caca");
                        }
                    });

        } else if (dItem.getTitle() != null) {
            drawerHolder.headerLayout.setVisibility(LinearLayout.VISIBLE);
            drawerHolder.itemLayout.setVisibility(LinearLayout.INVISIBLE);
            drawerHolder.spinnerLayout.setVisibility(LinearLayout.INVISIBLE);
            drawerHolder.title.setText(dItem.getTitle());

        } else {

            drawerHolder.headerLayout.setVisibility(LinearLayout.INVISIBLE);
            drawerHolder.spinnerLayout.setVisibility(LinearLayout.INVISIBLE);
            drawerHolder.itemLayout.setVisibility(LinearLayout.VISIBLE);

            drawerHolder.icon.setImageDrawable(view.getResources().getDrawable(
                    dItem.getImgResID()));
            drawerHolder.ItemName.setText(dItem.getItemName());

        }
        return view;
    }

    private static class DrawerItemHolder {
        TextView ItemName, title;
        ImageView icon;
        LinearLayout headerLayout, itemLayout, spinnerLayout;
        Spinner spinner;
    }

    private void loadFragment(Fragment fragment, String tag, String title) {
        FragmentTransaction transaction = ((Activity) context).getFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);

        transaction.replace(R.id.content_frame, fragment);
        transaction.addToBackStack(tag);

        ((Activity) context).setTitle(title);
        transaction.commit();
    }
}