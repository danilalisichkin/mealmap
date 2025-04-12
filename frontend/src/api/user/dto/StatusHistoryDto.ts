import { StatusEvent } from "../enums/StatusEvent";

export interface StatusHistoryDto {
    id: number;
    userId: string; // UUID as a string
    newStatus: StatusEvent;
    reason: string;
    changedBy: string; // UUID as a string
    eventAt: string; // ISO date-time string
}