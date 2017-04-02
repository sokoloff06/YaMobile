package com.example.sokol.yandexmobilization;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.sokol.yandexmobilization.data.LangsRepository;

import java.util.ArrayList;

/**
 * Created by sokol on 22.03.2017.
 */

public class LangsAdapter extends RecyclerView.Adapter<LangsAdapter.LangViewHolder> {


    private ArrayList<String> langNames;
    private LangsRepository langsRepository;
    private OnLangSelectedListener onSelectedListener;
    private CheckBox checkedBox;
    private int checkedPos;

    public LangsAdapter(LangsRepository langsRepository, OnLangSelectedListener onSelectedListener, String chosenLangName) {
        this.langNames = langsRepository.getLangNames();
        this.langsRepository = langsRepository;
        this.onSelectedListener = onSelectedListener;
        checkedPos = langNames.indexOf(chosenLangName);
        notifyDataSetChanged();
    }

    @Override
    public LangViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.lang_item, parent, false);
        return new LangViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(LangViewHolder holder, int position) {
        holder.textViewLang.setText(langNames.get(position));
        if (position == checkedPos) {
            holder.checkBox.setChecked(true);
            checkedBox = holder.checkBox;
        }
        //Since RecyclerView is reusing ViewHolder we have to uncheck CheckBox to avoid duplicating of checked CheckBoxes
        else if (holder.checkBox.isChecked()) {
            holder.checkBox.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return langNames.size();
    }

    public String getItemCode(int position) {
        String langName = langNames.get(position);
        return langsRepository.getLangCode(langName);
    }

    public String getItemName(int position) {
        return langNames.get(position);
    }

    public interface OnLangSelectedListener {
        void onLangSelected(int position);
    }

    class LangViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewLang;
        private CheckBox checkBox;

        public LangViewHolder(View itemView) {
            super(itemView);
            textViewLang = (TextView) itemView.findViewById(R.id.text_view_lang);
            checkBox = (CheckBox) itemView.findViewById(R.id.lang_check);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkedBox != null) {
                        checkedBox.setChecked(false);
                    }
                    checkBox.setChecked(true);
                    checkedPos = getAdapterPosition();
                    checkedBox = checkBox;
                    onSelectedListener.onLangSelected(getAdapterPosition());
                }
            });
        }
    }
}

