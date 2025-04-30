import React from "react";
import { ProductDto } from "../../../api/product/dto/ProductDto";
import ProductCard from "../ProductCard/ProductCard";
import "./Catalog.css";

interface CatalogProps {
  products: ProductDto[];
}

const Catalog: React.FC<CatalogProps> = ({ products }) => {
  return (
    <div className="grid grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-3">
      {products.map((product) => (
        <ProductCard key={product.id} product={product} />
      ))}
    </div>
  );
};

export default Catalog;
