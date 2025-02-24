export type leaveApplicationsType = {
  id: string;
  leaveType: string;
  reason: string;
  status: string;
  startDate: string;
  endDate: string;
  applicationDate: Date | null;
  employeeId: string;
  employeeName: string;
};
