import React from "react";
import ProductCardPlaceholder from "../ProductCardPlaceholder/ProductCardPlaceholder";

interface CatalogPlaceholderProps {
    numberOfElements: number;
}

const CatalogPlaceholder: React.FC<CatalogPlaceholderProps> = ({numberOfElements}) => {
  return (
    <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
      {Array.from({ length: numberOfElements }).map((_, index) => (
        <ProductCardPlaceholder key={"product-placeholder-" + index} />
      ))}
    </div>
  );
};

export default CatalogPlaceholder;
