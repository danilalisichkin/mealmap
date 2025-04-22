import { Address } from "../../common/dto/Address";
import { OrderItemCreationDto } from "./OrderItemCreationDto";

export interface OrderCreationDto {
  deliveryAddress: Address; // Assuming Address is a string
  promoCode: string; // Between 2 and 20 characters
  items: OrderItemCreationDto[]; // Must not be empty and within MAX_ITEMS_PER_ORDER
}
