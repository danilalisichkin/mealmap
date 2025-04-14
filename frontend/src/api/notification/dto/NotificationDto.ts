import { Channel } from "../enums/Channel";
import { NotificationStatus } from "../enums/NotificationStatus";

export interface NotificationDto {
  id: string; // ObjectId as a string
  userId: string;
  channel: Channel;
  status: NotificationStatus;
  subject: string;
  message: string;
  sentAt: string; // ISO date-time string
}
