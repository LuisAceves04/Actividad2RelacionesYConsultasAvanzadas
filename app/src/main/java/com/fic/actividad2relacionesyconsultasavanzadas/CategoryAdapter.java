package com.fic.actividad2relacionesyconsultasavanzadas;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private List<CategoryWithNotes> categoryList;

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category_with_notes, parent, false);
        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        if (categoryList != null) {
            CategoryWithNotes currentItem = categoryList.get(position);

            holder.textCategoryName.setText(currentItem.category.getCategory_name());

            if (currentItem.notes != null && !currentItem.notes.isEmpty()) {
                StringBuilder notasBuilder = new StringBuilder();

                for (Note note : currentItem.notes) {
                    notasBuilder.append("• ").append(note.getNote_title()).append("\n");
                }
                holder.textNotesList.setText(notasBuilder.toString());
            } else {
                holder.textNotesList.setText("Sin notas en esta categoría");
            }
        }
    }

    @Override
    public int getItemCount() {
        if (categoryList != null)
            return categoryList.size();
        else
            return 0;
    }

    public void setCategories(List<CategoryWithNotes> categories) {
        this.categoryList = categories;
        notifyDataSetChanged();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        private final TextView textCategoryName;
        private final TextView textNotesList;

        private CategoryViewHolder(View itemView) {
            super(itemView);
            textCategoryName = itemView.findViewById(R.id.tvCategoryName);
            textNotesList = itemView.findViewById(R.id.tvNotesList);
        }
    }
}