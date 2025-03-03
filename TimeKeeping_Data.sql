USE [TimeKeeping]
GO
SET IDENTITY_INSERT [dbo].[department] ON 

INSERT [dbo].[department] ([id], [name]) VALUES (1, N'Human Resources')
INSERT [dbo].[department] ([id], [name]) VALUES (2, N'Finance')
INSERT [dbo].[department] ([id], [name]) VALUES (3, N'Developer')
SET IDENTITY_INSERT [dbo].[department] OFF
GO
SET IDENTITY_INSERT [dbo].[position] ON 

INSERT [dbo].[position] ([id], [name]) VALUES (1, N'Manager')
INSERT [dbo].[position] ([id], [name]) VALUES (2, N'Accountant')
INSERT [dbo].[position] ([id], [name]) VALUES (3, N'Seller')
INSERT [dbo].[position] ([id], [name]) VALUES (4, N'Developer')
SET IDENTITY_INSERT [dbo].[position] OFF
GO
SET IDENTITY_INSERT [dbo].[role] ON 

INSERT [dbo].[role] ([roleid], [active], [name]) VALUES (1, 1, N'Admin')
INSERT [dbo].[role] ([roleid], [active], [name]) VALUES (2, 1, N'Finance')
INSERT [dbo].[role] ([roleid], [active], [name]) VALUES (3, 1, N'HR')
INSERT [dbo].[role] ([roleid], [active], [name]) VALUES (4, 1, N'Employee')
SET IDENTITY_INSERT [dbo].[role] OFF
GO
SET IDENTITY_INSERT [dbo].[region] ON 

INSERT [dbo].[region] ([regionid], [minimum_wage], [region_name]) VALUES (1, 4680000, N'Region 1')
INSERT [dbo].[region] ([regionid], [minimum_wage], [region_name]) VALUES (2, 4160000, N'Region 2')
INSERT [dbo].[region] ([regionid], [minimum_wage], [region_name]) VALUES (3, 3640000, N'Region 3')
INSERT [dbo].[region] ([regionid], [minimum_wage], [region_name]) VALUES (4, 3250000, N'Region 4')
SET IDENTITY_INSERT [dbo].[region] OFF
GO
SET IDENTITY_INSERT [dbo].[salary_template] ON 

INSERT [dbo].[salary_template] ([salaryid], [base_salary], [effective_date], [expiry_date], [grade_name], [regionid]) VALUES (1, 5000000, CAST(N'2024-09-08' AS Date), CAST(N'2024-09-30' AS Date), N'Junior', 1)
SET IDENTITY_INSERT [dbo].[salary_template] OFF
GO
SET IDENTITY_INSERT [dbo].[account] ON 

INSERT [dbo].[account] ([accountid], [address], [birth_date], [code_bank], [email], [full_name], [gender], [hire_date], [image_paths], [password], [phone_number], [status], [username], [department_id], [position_id], [role_id], [salaryid]) VALUES (1, N'Can Tho', CAST(N'2004-11-17T00:00:00.0000000' AS DateTime2), 123, N'lhtronga22027@cusc.ctu.edu.vn', N'Hoàn Trọng ', N'Male', CAST(N'2024-07-09T00:00:00.0000000' AS DateTime2), N'["/Data/HoanTrong/1.jpeg","/Data/HoanTrong/2.jpeg"]', N'$2y$10$SshORO.HPI2YqQb.4tpSJec/Jwo4Pcm89wM4/84rQ9sVIxFP48JmS', N'', N'Active', N'lhtronga22027@cusc.ctu.edu.vn', 1, 1, 4, NULL)
INSERT [dbo].[account] ([accountid], [address], [birth_date], [code_bank], [email], [full_name], [gender], [hire_date], [image_paths], [password], [phone_number], [status], [username], [department_id], [position_id], [role_id], [salaryid]) VALUES (2, N'Can Tho', CAST(N'2004-11-17T00:00:00.0000000' AS DateTime2), 123, N'user@cusc.ctu.edu.vn', N'Hoàn Trọng ', N'Male', CAST(N'2024-07-09T00:00:00.0000000' AS DateTime2), N'["/Data/HoanTrong/1.jpeg","/Data/HoanTrong/2.jpeg"]', N'$2y$10$SshORO.HPI2YqQb.4tpSJec/Jwo4Pcm89wM4/84rQ9sVIxFP48JmS', N'', N'Active', N'user@cusc.ctu.edu.vn', 1, 1, 4, NULL)
INSERT [dbo].[account] ([accountid], [address], [birth_date], [code_bank], [email], [full_name], [gender], [hire_date], [image_paths], [password], [phone_number], [status], [username], [department_id], [position_id], [role_id], [salaryid]) VALUES (3, N'Can Tho', CAST(N'2024-09-03T00:00:00.0000000' AS DateTime2), 123, N'hoantrong171124@gmail.com', N'Luong Hoan Trong', N'Male', CAST(N'2024-09-10T00:00:00.0000000' AS DateTime2), N'["/Data/LuongHoanTrong/1.jpeg","/Data/LuongHoanTrong/2.jpeg","/Data/LuongHoanTrong/3.jpeg"]', N'$2a$10$M.anyM20b33jbF49WsRK9uqBWk.jc6uxTBHPh8apfAeFB1ogx8RQO', N'0762956806', N'Active', N'hoantrong171124@gmail.com', NULL, NULL, 2, NULL)
INSERT [dbo].[account] ([accountid], [address], [birth_date], [code_bank], [email], [full_name], [gender], [hire_date], [image_paths], [password], [phone_number], [status], [username], [department_id], [position_id], [role_id], [salaryid]) VALUES (4, N'Can Tho', CAST(N'2024-09-03T00:00:00.0000000' AS DateTime2), 123, N'ntdanga22032@cusc.ctu.edu.vn', N'Nguyễn Thanh Đăng', N'Male', CAST(N'2024-09-02T00:00:00.0000000' AS DateTime2), N'["/Data/LuongHoanTrong/1.jpeg","/Data/LuongHoanTrong/2.jpeg","/Data/NguyenThanhÐang/1.jpeg","/Data/NguyenThanhÐang/1.jpeg"]', N'$2a$10$816mBr2Rmve4fwUdn.0yLuGjFpAHDPiVMKOx21tQFFfQXJUXizNmy', N'0762956806', N'Active', N'ntdanga22032@cusc.ctu.edu.vn', 1, 1, 4, NULL)
SET IDENTITY_INSERT [dbo].[account] OFF
GO
SET IDENTITY_INSERT [dbo].[activity] ON 

INSERT [dbo].[activity] ([activity_id], [activity_name], [budget], [description], [location], [start_time], [type]) VALUES (1, N'Team Building', 10000000, N'Join us for the event this weekend!', N'VN', CAST(N'2024-09-30T00:00:00.0000000' AS DateTime2), N'OPTIONAL')
INSERT [dbo].[activity] ([activity_id], [activity_name], [budget], [description], [location], [start_time], [type]) VALUES (3, N'Internal Seminar', 5000000, N'Workshops help employees grasp new knowledge and create opportunities to exchange practical experiences.', N'VN', CAST(N'2024-10-02T08:00:00.0000000' AS DateTime2), N'MANDATORY')
INSERT [dbo].[activity] ([activity_id], [activity_name], [budget], [description], [location], [start_time], [type]) VALUES (12, N'Year End Party', 8000000, N'This is an opportunity for members to look back at important milestones, express gratitude for each person''s contributions and welcome a new year together.', N'VN', CAST(N'2025-01-30T17:00:00.0000000' AS DateTime2), N'OPTIONAL')
SET IDENTITY_INSERT [dbo].[activity] OFF
GO
SET IDENTITY_INSERT [dbo].[activity_notification] ON 

INSERT [dbo].[activity_notification] ([id], [content], [is_read], [notification_time], [account_id], [activity_id]) VALUES (2, N'[OPTIONAL] Team Building - Confirm Your Attendance', 1, CAST(N'2024-09-30T00:00:00.0000000' AS DateTime2), 4, 1)
INSERT [dbo].[activity_notification] ([id], [content], [is_read], [notification_time], [account_id], [activity_id]) VALUES (10005, N'[OPTIONAL] Year End Party - Confirm Your Attendance', 0, CAST(N'2024-10-05T02:46:14.9769470' AS DateTime2), 4, 12)
SET IDENTITY_INSERT [dbo].[activity_notification] OFF
GO
SET IDENTITY_INSERT [dbo].[shift] ON 

INSERT [dbo].[shift] ([shift_id], [end_time], [shift_name], [start_time]) VALUES (1, CAST(N'11:30:00' AS Time), N'Morning', CAST(N'07:30:00' AS Time))
INSERT [dbo].[shift] ([shift_id], [end_time], [shift_name], [start_time]) VALUES (2, CAST(N'17:00:00' AS Time), N'Afternoon', CAST(N'13:00:00' AS Time))
INSERT [dbo].[shift] ([shift_id], [end_time], [shift_name], [start_time]) VALUES (3, CAST(N'17:00:00' AS Time), N'Fulltime', CAST(N'09:00:00' AS Time))
SET IDENTITY_INSERT [dbo].[shift] OFF
GO
SET IDENTITY_INSERT [dbo].[participation] ON 

INSERT [dbo].[participation] ([participate_id], [reason], [status], [account_id], [id]) VALUES (1, NULL, N'JOINED', 4, 10005)
INSERT [dbo].[participation] ([participate_id], [reason], [status], [account_id], [id]) VALUES (2, NULL, N'JOINED', 4, 2)
SET IDENTITY_INSERT [dbo].[participation] OFF
GO
SET IDENTITY_INSERT [dbo].[payroll] ON 

INSERT [dbo].[payroll] ([payrollid], [created_at], [gross_salary], [net_salary], [pay_date], [accountid], [salaryid]) VALUES (1, CAST(N'2024-09-26T00:00:00.0000000' AS DateTime2), 100000, 100000, CAST(N'2024-09-26' AS Date), 1, 1)
SET IDENTITY_INSERT [dbo].[payroll] OFF
GO
SET IDENTITY_INSERT [dbo].[job] ON 

INSERT [dbo].[job] ([jobid], [closing_date], [description], [experience_years], [location], [path], [posting_date], [requirements], [salary_range], [status], [title]) VALUES (8, CAST(N'2024-10-07T00:00:00.0000000' AS DateTime2), N'fsdf', 2, N'VN', N'/images/Screenshot 2024-03-05 123338.png', CAST(N'2024-10-01T00:00:00.0000000' AS DateTime2), N'dffsd', N'123456', N'Open', N'dfs')
SET IDENTITY_INSERT [dbo].[job] OFF
GO
SET IDENTITY_INSERT [dbo].[recruitment] ON 

INSERT [dbo].[recruitment] ([recruitmentid], [description], [end_date], [interview_date], [interview_type], [location], [name], [notes], [start_date], [status], [account_id], [job_id]) VALUES (9, N'sd', CAST(N'2024-10-17T00:00:00.0000000' AS DateTime2), CAST(N'2024-10-17T16:31:00.0000000' AS DateTime2), N'xcvbsdfg', N'Muong Thanh', N'vùng 2', N'as', CAST(N'2024-10-22T00:00:00.0000000' AS DateTime2), N'cvbgnh', 1, 8)
SET IDENTITY_INSERT [dbo].[recruitment] OFF
GO
SET IDENTITY_INSERT [dbo].[work_schedule] ON 
GO
INSERT [dbo].[work_schedule] ([schedule_id], [date], [status], [account_id], [shift_id]) VALUES (1, CAST(N'2024-10-14' AS Date), N'APPROVED', 4, 2)
GO
INSERT [dbo].[work_schedule] ([schedule_id], [date], [status], [account_id], [shift_id]) VALUES (2, CAST(N'2024-10-15' AS Date), N'APPROVED', 4, 1)
GO
INSERT [dbo].[work_schedule] ([schedule_id], [date], [status], [account_id], [shift_id]) VALUES (5, CAST(N'2024-10-18' AS Date), N'APPROVED', 4, 1)
GO
INSERT [dbo].[work_schedule] ([schedule_id], [date], [status], [account_id], [shift_id]) VALUES (6, CAST(N'2024-10-19' AS Date), N'APPROVED', 4, 1)
GO
INSERT [dbo].[work_schedule] ([schedule_id], [date], [status], [account_id], [shift_id]) VALUES (7, CAST(N'2024-10-14' AS Date), N'APPROVED', 1, 1)
GO
INSERT [dbo].[work_schedule] ([schedule_id], [date], [status], [account_id], [shift_id]) VALUES (8, CAST(N'2024-10-15' AS Date), N'APPROVED', 1, 2)
GO
INSERT [dbo].[work_schedule] ([schedule_id], [date], [status], [account_id], [shift_id]) VALUES (9, CAST(N'2024-10-17' AS Date), N'APPROVED', 4, 3)
GO
INSERT [dbo].[work_schedule] ([schedule_id], [date], [status], [account_id], [shift_id]) VALUES (10, CAST(N'2024-10-17' AS Date), N'PENDING', 4, 2)
GO
INSERT [dbo].[work_schedule] ([schedule_id], [date], [status], [account_id], [shift_id]) VALUES (11, CAST(N'2024-10-16' AS Date), N'APPROVED', 1, 1)
GO
INSERT [dbo].[work_schedule] ([schedule_id], [date], [status], [account_id], [shift_id]) VALUES (12, CAST(N'2024-10-16' AS Date), N'APPROVED', 4, 2)
GO
INSERT [dbo].[work_schedule] ([schedule_id], [date], [status], [account_id], [shift_id]) VALUES (13, CAST(N'2024-10-18' AS Date), N'APPROVED', 1, 2)
GO
INSERT [dbo].[work_schedule] ([schedule_id], [date], [status], [account_id], [shift_id]) VALUES (14, CAST(N'2024-10-19' AS Date), N'APPROVED', 1, 2)
GO
INSERT [dbo].[work_schedule] ([schedule_id], [date], [status], [account_id], [shift_id]) VALUES (15, CAST(N'2024-10-20' AS Date), N'APPROVED', 1, 3)
GO
INSERT [dbo].[work_schedule] ([schedule_id], [date], [status], [account_id], [shift_id]) VALUES (16, CAST(N'2024-10-20' AS Date), N'REJECTED', 4, 3)
GO
SET IDENTITY_INSERT [dbo].[work_schedule] OFF
GO
SET IDENTITY_INSERT [dbo].[bonus] ON 

INSERT [dbo].[bonus] ([bonusid], [amount], [bonus_type], [payroll_id]) VALUES (1, 100, N'test', NULL)
SET IDENTITY_INSERT [dbo].[bonus] OFF
GO
SET IDENTITY_INSERT [dbo].[deduction] ON 

INSERT [dbo].[deduction] ([deductionid], [amount], [deduction_type], [description], [payroll_id]) VALUES (1, 20, N'late', N'Come in later than working hours', 1)
SET IDENTITY_INSERT [dbo].[deduction] OFF
GO
