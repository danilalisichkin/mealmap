import { Channel } from "../enums/Channel";
import { NotificationStatus } from "../enums/NotificationStatus";

export interface NotificationFilter {
  userId?: string; // UUID as a string
  channel?: Channel;
  status?: NotificationStatus;
  sentAtStart?: string; // ISO date-time string
  sentAtEnd?: string; // ISO date-time string
}
