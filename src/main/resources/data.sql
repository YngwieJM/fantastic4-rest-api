SET IDENTITY_INSERT [corez].[MstAkses] ON
;

INSERT INTO [corez].[MstAkses] ([CreatedBy], [CreatedDate], [ID], [ModifiedBy], [ModifiedDate], [Nama], [Deskripsi]) VALUES (N'1', N'2025-05-24 20:36:04.000000', N'1', NULL, NULL, N'Admin', N'Administrator')
;

INSERT INTO [corez].[MstAkses] ([CreatedBy], [CreatedDate], [ID], [ModifiedBy], [ModifiedDate], [Nama], [Deskripsi]) VALUES (N'1', N'2025-05-24 20:36:04.000000', N'2', NULL, NULL, N'User', N'User Doankz')
;

SET IDENTITY_INSERT [corez].[MstAkses] OFF
;

SET IDENTITY_INSERT [corez].[MstMenu] ON
;

INSERT INTO [corez].[MstMenu] ([CreatedBy], [CreatedDate], [ID], [ModifiedBy], [ModifiedDate], [Nama], [Path], [Deskripsi]) VALUES (N'1', N'2025-05-24 20:36:04.000000', N'1', NULL, NULL, N'User', N'/user', N'Untuk Melihat Info User')
;

INSERT INTO [corez].[MstMenu] ([CreatedBy], [CreatedDate], [ID], [ModifiedBy], [ModifiedDate], [Nama], [Path], [Deskripsi]) VALUES (N'1', N'2025-05-24 20:36:04.000000', N'2', NULL, NULL, N'Facility', N'/facility', N'Untuk Melihat Informasi Facility')
;

INSERT INTO [corez].[MstMenu] ([CreatedBy], [CreatedDate], [ID], [ModifiedBy], [ModifiedDate], [Nama], [Path], [Deskripsi]) VALUES (N'1', N'2025-05-24 20:36:04.000000', N'3', NULL, NULL, N'Manage Room', N'/manage-room', N'Untuk Informasi Room')
;

INSERT INTO [corez].[MstMenu] ([CreatedBy], [CreatedDate], [ID], [ModifiedBy], [ModifiedDate], [Nama], [Path], [Deskripsi]) VALUES (N'1', N'2025-05-24 20:36:04.000000', N'4', NULL, NULL, N'List Booking Room', N'/list-booking', N'Untuk Lihat Info List Booking Room')
;

INSERT INTO [corez].[MstMenu] ([CreatedBy], [CreatedDate], [ID], [ModifiedBy], [ModifiedDate], [Nama], [Path], [Deskripsi]) VALUES (N'1', N'2025-05-24 20:36:04.000000', N'5', NULL, NULL, N'Booking Room', N'/booking-room', N'Untuk Booking Room User')
;

INSERT INTO [corez].[MstMenu] ([CreatedBy], [CreatedDate], [ID], [ModifiedBy], [ModifiedDate], [Nama], [Path], [Deskripsi]) VALUES (N'1', N'2025-05-24 20:36:04.000000', N'6', NULL, NULL, N'Activity', N'/activity', N'Untuk Lihat Info Aktifitas')
;

INSERT INTO [corez].[MstMenu] ([CreatedBy], [CreatedDate], [ID], [ModifiedBy], [ModifiedDate], [Nama], [Path], [Deskripsi]) VALUES (N'1', N'2025-05-24 20:36:04.000000', N'7', NULL, NULL, N'Change Password', N'/change-password', N'Untuk Ganti Password')
;

SET IDENTITY_INSERT [corez].[MstMenu] OFF
;

    INSERT INTO [corez].[MapAksesMenu] ([IDAkses], [IDMenu]) VALUES (N'1', N'1')
;

INSERT INTO [corez].[MapAksesMenu] ([IDAkses], [IDMenu]) VALUES (N'1', N'2')
;

INSERT INTO [corez].[MapAksesMenu] ([IDAkses], [IDMenu]) VALUES (N'1', N'3')
;

INSERT INTO [corez].[MapAksesMenu] ([IDAkses], [IDMenu]) VALUES (N'1', N'4')
;

INSERT INTO [corez].[MapAksesMenu] ([IDAkses], [IDMenu]) VALUES (N'1', N'5')
;

INSERT INTO [corez].[MapAksesMenu] ([IDAkses], [IDMenu]) VALUES (N'1', N'6')
;

INSERT INTO [corez].[MapAksesMenu] ([IDAkses], [IDMenu]) VALUES (N'1', N'7')
;

INSERT INTO [corez].[MapAksesMenu] ([IDAkses], [IDMenu]) VALUES (N'2', N'5')
;

INSERT INTO [corez].[MapAksesMenu] ([IDAkses], [IDMenu]) VALUES (N'2', N'6')
;

INSERT INTO [corez].[MapAksesMenu] ([IDAkses], [IDMenu]) VALUES (N'2', N'7')
;

INSERT INTO [corez].[MstUser] ([IDAkses], [Email], [Nama], [NoTelp], [UserID], [Password], [Departemen], [Jabatan]) VALUES (N'1', N'cumi@gmail.com', N'cumi', N'628239283', N'paul.cumi', N'Paul@321', N'CumiCumi', N'User')
;
