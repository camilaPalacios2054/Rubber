package app.santo.rubber.MenuCardView;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import app.santo.rubber.R;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    private List<MenuElement>mData;
    private LayoutInflater mInflater;
    private Context context;

    public MenuAdapter(List<MenuElement>itemMenu,Context context)
    {
        this.mInflater=LayoutInflater.from(context);
        this.context=context;
        this.mData=itemMenu;
    }

    @Override
    public int getItemCount() {return mData.size();}

    @Override
    public MenuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mInflater.inflate(R.layout.menu_element,null);
        return new MenuAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MenuAdapter.ViewHolder holder, final int position) {
        holder.binData(mData.get(position));

    }

    public void setItems(List<MenuElement>items) {mData=items;}

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView iconImage,iconEstado;
        TextView nombre;

        ViewHolder(View itemView)
        {
            super(itemView);
            iconImage=itemView.findViewById(R.id.iconOpcion);
            iconEstado=itemView.findViewById(R.id.iconFinal);
            nombre=itemView.findViewById(R.id.tituloOpcion);
        }
        void binData(final MenuElement item)
        {
            iconImage.setColorFilter(Color.parseColor(item.getColor()), PorterDuff.Mode.SRC_IN);
            iconEstado.setColorFilter(Color.parseColor(item.getColor()), PorterDuff.Mode.SRC_IN);
            nombre.setText(item.getNombreOpcion());
        }
    }
}

