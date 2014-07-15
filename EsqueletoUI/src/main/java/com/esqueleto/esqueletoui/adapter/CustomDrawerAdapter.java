package com.esqueleto.esqueletoui.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.esqueleto.esqueletoui.R;
import com.esqueleto.esqueletoui.adapter.item.DrawerItem;
import com.esqueleto.esqueletoui.adapter.item.SpinnerItem;

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
                            TextView tv = (TextView) arg1.findViewById(R.id.text_main_name);
//                            Bundle arguments = new Bundle();
//                            //TODO: El id de la cuenta lo obtendremos del spinner del navigation drawer
//                            arguments.putInt("cuentaId", (Integer) tv.getTag());
//                            // Crear un nuevo fragmento y transacción
//                            ResumenFragment newFragment2 = ResumenFragment.newInstance(arguments);
//                            FragmentTransaction transaction2 = ((Activity) context).getFragmentManager().beginTransaction();
//
//                            // Reemplazar lo que esté en el fragment_container view con este fragmento,
//                            // y añadir transacción al back stack
//                            transaction2.replace(R.id.content_frame, newFragment2, ResumenFragment.TAG);
//                            transaction2.addToBackStack(null);
//
//                            //commit la trasacción
//                            transaction2.commit();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> arg0) {
                            // TODO Auto-generated method stub

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
}