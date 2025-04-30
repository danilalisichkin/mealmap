import React from "react";

interface SearchProps {
  text: string;
  placeHolder: string;
  onChange: (text: string) => void;
  onApply: () => void;
}

const Search: React.FC<SearchProps> = ({
  text,
  placeHolder,
  onChange,
  onApply,
}) => {
  return (
    <div className="relative w-full md:w-64">
      <div className="absolute left-3 top-2.5 text-gray-400">
        <button
          type="button"
          onClick={onApply}
          className="fas fa-search"
          aria-label="Search"
        ></button>
      </div>
      <input
        type="text"
        id="search-input"
        value={text}
        placeholder={placeHolder}
        className="w-full pl-10 pr-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-green-500 focus:border-green-500"
        onChange={(e) => onChange(e.target.value)}
        onKeyDown={(e) => {
          if (e.key === "Enter") onApply();
        }}
      />
    </div>
  );
};

export default Search;
