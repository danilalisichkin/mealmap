import React from "react";
import Sort from "../../commons/Sort/Sort";
import { NotificationSortField } from "../../../api/notification/enums/NotificationSortField";

const SORT_FIELDS = [
  { value: NotificationSortField.SENT_AT, label: "Дата отправки" },
];

interface NotificationSortProps {
  sortField: NotificationSortField;
  sortOrder: "asc" | "desc";
  onSortChange: (field: NotificationSortField, order: "asc" | "desc") => void;
}

const NotificationSort: React.FC<NotificationSortProps> = ({
  sortField,
  sortOrder,
  onSortChange,
}) => {
  return (
    <Sort
      sortField={sortField}
      sortOrder={sortOrder}
      fields={SORT_FIELDS}
      onSortChange={onSortChange}
    />
  );
};

export default NotificationSort;
