import { StatusEvent } from "../enums/StatusEvent";

export interface UserStatusHistoryFilter {
    statusEvent?: StatusEvent;
    startTime?: string; // ISO date-time string
    endTime?: string; // ISO date-time string
}