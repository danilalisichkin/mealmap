import { Role } from "../enums/Role";

export interface UserRegisterDto {
  phoneNumber: string; // Must match PHONE_BELARUS_FORMAT regex
  email: string; // Valid email format
  password: string; // Between 4 and 50 characters
  firstName: string; // Max 50 characters
  lastName: string; // Max 50 characters
  organizationId: number;
  role: Role;
}
