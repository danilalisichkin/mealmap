import React from "react";

interface CartDeliveryFormProps {
  deliveryData: {
    name: string;
    phone: string;
    address: string;
    comment: string;
  };
  onChange: (field: string, value: string) => void;
}

const CartDeliveryForm: React.FC<CartDeliveryFormProps> = ({
  deliveryData,
  onChange,
}) => {
  return (
    <form id="delivery-form">
      <div className="grid md:grid-cols-2 gap-4 mb-4">
        <div>
          <label
            htmlFor="name"
            className="block text-sm font-medium text-gray-700 mb-1"
          >
            Имя
          </label>
          <input
            type="text"
            id="name"
            name="name"
            value={deliveryData.name}
            onChange={(e) => onChange("name", e.target.value)}
            className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-green-500 focus:border-green-500"
            required
          />
        </div>
        <div>
          <label
            htmlFor="phone"
            className="block text-sm font-medium text-gray-700 mb-1"
          >
            Телефон
          </label>
          <input
            type="tel"
            id="phone"
            name="phone"
            value={deliveryData.phone}
            onChange={(e) => onChange("phone", e.target.value)}
            className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-green-500 focus:border-green-500"
            required
          />
        </div>
      </div>
      <div className="mb-4">
        <label
          htmlFor="address"
          className="block text-sm font-medium text-gray-700 mb-1"
        >
          Адрес
        </label>
        <input
          type="text"
          id="address"
          name="address"
          value={deliveryData.address}
          onChange={(e) => onChange("address", e.target.value)}
          className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-green-500 focus:border-green-500"
          required
        />
      </div>
      <div className="mb-4">
        <label
          htmlFor="comment"
          className="block text-sm font-medium text-gray-700 mb-1"
        >
          Комментарий к заказу
        </label>
        <textarea
          id="comment"
          name="comment"
          rows={3}
          value={deliveryData.comment}
          onChange={(e) => onChange("comment", e.target.value)}
          className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-green-500 focus:border-green-500"
        ></textarea>
      </div>
    </form>
  );
};

export default CartDeliveryForm;
