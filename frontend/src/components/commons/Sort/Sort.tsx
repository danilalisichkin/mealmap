import React from "react";
import "./Sort.css";

interface SortProps<T> {
  sortField: T;
  sortOrder: "asc" | "desc";
  fields: { value: T; label: string }[];
  onSortChange: (field: T, order: "asc" | "desc") => void;
}

const Sort = <T extends string>({
  sortField,
  sortOrder,
  fields,
  onSortChange,
}: SortProps<T>) => {
  const handleSortFieldChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    onSortChange(e.target.value as T, sortOrder);
  };

  const handleSortOrderChange = () => {
    const newOrder = sortOrder === "asc" ? "desc" : "asc";
    onSortChange(sortField, newOrder);
  };

  return (
    <div className="flex items-center justify-center gap-2 text-green-600 hover:text-green-800 border border-green-600 hover:border-green-800 px-4 py-2 md:py-0 rounded-lg transition-colors">
      <label htmlFor="sort-field">
        сортировать по:
      </label>
      <select
        id="sort-field"
        value={sortField}
        onChange={handleSortFieldChange}
        className="px-2 py-1 border border-gray-300 rounded-md text-sm text-green-600 hover:text-green-800"
      >
        {fields.map((field) => (
          <option key={field.value} value={field.value}>
            {field.label}
          </option>
        ))}
      </select>
      <button onClick={handleSortOrderChange}>
        {sortOrder === "asc" ? (
          <i className="fas fa-arrow-up mr-1"></i>
        ) : (
          <i className="fas fa-arrow-down mr-1"></i>
        )}
      </button>
    </div>
  );
};

export default Sort;
