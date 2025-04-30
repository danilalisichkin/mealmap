import React, { useState } from "react";
import "./Pagination.css";

interface PaginationProps {
  page: number;
  pageSize: number;
  totalPages: number;
  totalElements: number;
  onPageChange: (page: number) => void;
}

const Pagination: React.FC<PaginationProps> = ({
  page,
  pageSize,
  totalPages,
  totalElements,
  onPageChange,
}) => {
  const [currentPage, setCurrentPage] = useState(page);

  const handlePageChange = (newPage: number) => {
    if (newPage < 1 || newPage > totalPages) return;
    setCurrentPage(newPage);
    onPageChange(newPage);
  };

  return (
    <div className="flex flex-col items-center mt-8">
      <div className="flex items-center space-x-2">
        {currentPage > 1 && (
          <button
            className="page-btn px-3 py-1 rounded-md text-sm bg-gray-100 text-gray-700 hover:bg-gray-200"
            onClick={() => handlePageChange(currentPage - 1)}
          >
            &lt;
          </button>
        )}
        {renderPageButtons(currentPage, totalPages, handlePageChange)}
        {currentPage < totalPages && (
          <button
            className="page-btn px-3 py-1 rounded-md text-sm bg-gray-100 text-gray-700 hover:bg-gray-200"
            onClick={() => handlePageChange(currentPage + 1)}
          >
            &gt;
          </button>
        )}
      </div>
      <p className="mt-2 text-sm text-gray-600">
        {Math.min(pageSize * currentPage, totalElements)} из {totalElements}
      </p>
    </div>
  );
};

const renderPageButtons = (
  currentPage: number,
  totalPages: number,
  onPageChange: (page: number) => void
) => {
  const maxButtons = 5;
  let start = Math.max(1, currentPage - Math.floor(maxButtons / 2));
  let end = Math.min(totalPages, start + maxButtons - 1);

  if (end - start + 1 < maxButtons) {
    start = Math.max(1, end - maxButtons + 1);
  }

  const buttons = [];
  for (let i = start; i <= end; i++) {
    buttons.push(
      <button
        key={i}
        className={`page-btn px-3 py-1 rounded-md text-sm ${
          currentPage === i
            ? "bg-green-500 text-white"
            : "bg-gray-100 text-gray-700 hover:bg-gray-200"
        }`}
        onClick={() => onPageChange(i)}
      >
        {i}
      </button>
    );
  }

  return buttons;
};

export default Pagination;
