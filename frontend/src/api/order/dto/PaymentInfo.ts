import { PaymentStatus } from "../enums/PaymentStatus";

export interface PaymentInfo {
  totalAmount: number;
  discountAmount: number;
  paymentId: number;
  paymentStatus: PaymentStatus;
}
