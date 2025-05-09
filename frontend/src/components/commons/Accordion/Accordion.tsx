import React, { useState } from "react";
import "./Accordion.css";

interface AccordionItem {
  title: string;
  content: React.ReactNode;
}

interface AccordionProps {
  items: AccordionItem[];
}

const Accordion: React.FC<AccordionProps> = ({ items }) => {
  const [activeIndex, setActiveIndex] = useState<number | null>(null);

  const toggleAccordion = (index: number) => {
    setActiveIndex(activeIndex === index ? null : index);
  };

  return (
    <div className="accordion">
      {items.map((item, index) => (
        <div key={index} className="accordion-item">
          <div
            className="accordion-header px-4 py-5 cursor-pointer flex justify-between items-center"
            onClick={() => toggleAccordion(index)}
          >
            <h3 className="font-medium text-gray-800">{item.title}</h3>
            <i
              className={`fas fa-chevron-down text-gray-400 transition-transform duration-200 ${
                activeIndex === index ? "rotate-180" : ""
              }`}
            ></i>
          </div>
          <div
            className="accordion-content px-4"
            style={{
              maxHeight: activeIndex === index ? "500px" : "0",
              opacity: activeIndex === index ? 1 : 0,
            }}
          >
            <div className="pb-5 text-gray-600">{item.content}</div>
          </div>
        </div>
      ))}
    </div>
  );
};

export default Accordion;
