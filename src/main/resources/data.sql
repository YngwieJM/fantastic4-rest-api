SET IDENTITY_INSERT [corez].[MstAkses] ON
;

IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[corez].[MapAksesMenu]') AND type IN ('U'))
	DROP TABLE [corez].[MapAksesMenu]
;

CREATE TABLE [corez].[MapAksesMenu] (
  [IDAkses] bigint  NOT NULL,
  [IDMenu] bigint  NOT NULL
)
;

-- ----------------------------
-- Foreign Keys structure for table MapAksesMenu
-- ----------------------------
ALTER TABLE [corez].[MapAksesMenu] ADD CONSTRAINT [foreign-key-akses-menu-to-akses] FOREIGN KEY ([IDAkses]) REFERENCES [corez].[MstAkses] ([ID]) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE [corez].[MapAksesMenu] ADD CONSTRAINT [foreign-key-akses-menu-to-menu] FOREIGN KEY ([IDMenu]) REFERENCES [corez].[MstMenu] ([ID]) ON DELETE NO ACTION ON UPDATE NO ACTION
;

EXEC sp_addextendedproperty
'MS_Description', N'Foreign Key MapAksesMenu to MstAkses',
'SCHEMA', N'corez',
'TABLE', N'MapAksesMenu',
'CONSTRAINT', N'foreign-key-akses-menu-to-akses'
;

EXEC sp_addextendedproperty
'MS_Description', N'Foreign Key MapAksesMenu to MstMenu',
'SCHEMA', N'corez',
'TABLE', N'MapAksesMenu',
'CONSTRAINT', N'foreign-key-akses-menu-to-menu'
;

ALTER TABLE [corez].[MapAksesMenu] SET (LOCK_ESCALATION = TABLE)
;


ALTER TABLE [corez].[MapAksesMenu] SET (LOCK_ESCALATION = TABLE)
;

--INSERT INTO [corez].[MstFasilitas] ([Jumlah], [CreatedBy], [CreatedDate], [ModifiedBy], [ModifiedDate], [FasilitasID], [NamaFasilitas], [RuanganID]) VALUES (N'10', N'1', N'2025-05-29 16:34:16.000000', NULL, NULL, N'FS1', N'Kursi', N'RM0201')
--;

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

INSERT INTO [corez].[MstUser] ([IsRegistered], [CreatedDate], [IDAkses], [ModifiedDate], [Username], [Email], [Nama], [NoTelp], [UserID], [OTP], [Password], [TokenEstafet], [Departemen], [Jabatan], [CreatedBy], [ModifiedBy]) VALUES (N'1', N'2025-05-27 01:16:04.000000', N'1', NULL, N'Admin', N'admin@gmail.com', N'Admin', N'+62812345678', N'Admin123', N'', N'$2a$11$sCbuyD61iIe/PrP0Rs4AkuyzLmJfcGaq4lNMOm8rCZD2SeCqb1cP.', NULL, N'Admin', N'Admin', N'Admin', NULL)
;

INSERT INTO [corez].[MstUser] ([IsRegistered], [CreatedBy], [CreatedDate], [IDAkses], [ModifiedBy], [ModifiedDate], [Email], [Nama], [Username], [NoTelp], [UserID], [OTP], [Password], [TokenEstafet], [Departemen], [Jabatan]) VALUES (N'1', N'1', N'2025-05-27 01:15:37.000000', N'1', NULL, NULL, N'cumi@gmail.com', N'cumi', N'Paul123', N'628239283', N'paul.cumi', NULL, N'Paul@321', NULL, N'CumiCumi', N'User')
;

INSERT INTO [corez].[MstUser] ([IsRegistered], [CreatedBy], [CreatedDate], [IDAkses], [ModifiedBy], [ModifiedDate], [Email], [Nama], [Username], [NoTelp], [UserID], [OTP], [Password], [TokenEstafet], [Departemen], [Jabatan]) VALUES (N'1', N'1', N'2025-05-27 01:16:04.000000', N'1', NULL, NULL, N'yngwie@gmail.com', N'yngwie', N'JYW33', N'+62812342781', N'Yng1998', NULL, N'$2a$11$zlY6pD3keBkXNb4d65yeRuz4uMD1.21uGnhknxtEDXQUZvUIlgGGK', NULL, N'IT', N'Core')
;

INSERT INTO [corez].[MstRuangan] ([MaxKapasitas], [MinKapasitas], [CreatedBy], [CreatedDate], [ModifiedBy], [ModifiedDate], [Lokasi], [NamaRuangan], [RuanganID]) VALUES (N'10', N'1', N'1', N'2025-05-29 16:33:22.000000', NULL, NULL, N'Lantai 1', N'Ruang Meeting 1', N'R001')
;

INSERT INTO [corez].[MstRuangan] ([MaxKapasitas], [MinKapasitas], [CreatedBy], [CreatedDate], [ModifiedBy], [ModifiedDate], [Lokasi], [NamaRuangan], [RuanganID]) VALUES (N'10', N'5', N'1', N'2025-05-29 16:33:22.000000', NULL, NULL, N'Lantai 2', N'Ruang Meeting 2', N'R002')
;

INSERT INTO [corez].[MstRuangan] ([MaxKapasitas], [MinKapasitas], [CreatedBy], [CreatedDate], [ModifiedBy], [ModifiedDate], [Lokasi], [NamaRuangan], [RuanganID]) VALUES (N'50', N'10', N'1', N'2025-05-29 16:33:22.000000', NULL, NULL, N'Lantai 3', N'Ruang Meeting 3', N'R003')
;

INSERT INTO [corez].[MstRuangan] ([MaxKapasitas], [MinKapasitas], [CreatedBy], [CreatedDate], [ModifiedBy], [ModifiedDate], [Lokasi], [NamaRuangan], [RuanganID]) VALUES (N'50', N'1', N'1', N'2025-05-29 16:33:22.000000', NULL, NULL, N'Lantai 4', N'Ruang Meeting 4', N'R004')
;

INSERT INTO [corez].[MstFasilitas] ([Jumlah], [CreatedBy], [CreatedDate], [ModifiedBy], [ModifiedDate], [FasilitasID], [NamaFasilitas], [RuanganID]) VALUES (N'10', N'1', N'2025-05-29 16:33:22.000000', NULL, NULL, N'F0001', N'Kursi', N'R001')
;

INSERT INTO [corez].[MstFasilitas] ([Jumlah], [CreatedBy], [CreatedDate], [ModifiedBy], [ModifiedDate], [FasilitasID], [NamaFasilitas], [RuanganID]) VALUES (N'1', N'1', N'2025-05-29 16:33:22.000000', NULL, NULL, N'F0002', N'Proyektor LCD', N'R001')
;

INSERT INTO [corez].[MstFasilitas] ([Jumlah], [CreatedBy], [CreatedDate], [ModifiedBy], [ModifiedDate], [FasilitasID], [NamaFasilitas], [RuanganID]) VALUES (N'1', N'1', N'2025-05-29 16:33:22.000000', NULL, NULL, N'F0003', N'Papan Tulis', N'R001')
;

INSERT INTO [corez].[MstFasilitas] ([Jumlah], [CreatedBy], [CreatedDate], [ModifiedBy], [ModifiedDate], [FasilitasID], [NamaFasilitas], [RuanganID]) VALUES (N'10', N'1', N'2025-05-29 16:33:22.000000', NULL, NULL, N'F0004', N'Kursi', N'R002')
;

INSERT INTO [corez].[MstFasilitas] ([Jumlah], [CreatedBy], [CreatedDate], [ModifiedBy], [ModifiedDate], [FasilitasID], [NamaFasilitas], [RuanganID]) VALUES (N'1', N'1', N'2025-05-29 16:33:22.000000', NULL, NULL, N'F0005', N'Proyektor LCD', N'R002')
;

INSERT INTO [corez].[MstFasilitas] ([Jumlah], [CreatedBy], [CreatedDate], [ModifiedBy], [ModifiedDate], [FasilitasID], [NamaFasilitas], [RuanganID]) VALUES (N'1', N'1', N'2025-05-29 16:33:22.000000', NULL, NULL, N'F0006', N'Papan Tulis', N'R002')
;

INSERT INTO [corez].[MstFasilitas] ([Jumlah], [CreatedBy], [CreatedDate], [ModifiedBy], [ModifiedDate], [FasilitasID], [NamaFasilitas], [RuanganID]) VALUES (N'50', N'1', N'2025-05-29 16:33:22.000000', NULL, NULL, N'F0007', N'Kursi', N'R003')
;

INSERT INTO [corez].[MstFasilitas] ([Jumlah], [CreatedBy], [CreatedDate], [ModifiedBy], [ModifiedDate], [FasilitasID], [NamaFasilitas], [RuanganID]) VALUES (N'2', N'1', N'2025-05-29 16:33:22.000000', NULL, NULL, N'F0008', N'Proyektor LCD', N'R003')
;

INSERT INTO [corez].[MstFasilitas] ([Jumlah], [CreatedBy], [CreatedDate], [ModifiedBy], [ModifiedDate], [FasilitasID], [NamaFasilitas], [RuanganID]) VALUES (N'2', N'1', N'2025-05-29 16:33:22.000000', NULL, NULL, N'F0009', N'Papan Tulis', N'R003')
;

INSERT INTO [corez].[MstFasilitas] ([Jumlah], [CreatedBy], [CreatedDate], [ModifiedBy], [ModifiedDate], [FasilitasID], [NamaFasilitas], [RuanganID]) VALUES (N'50', N'1', N'2025-05-29 16:33:22.000000', NULL, NULL, N'F0010', N'Kursi', N'R003')
;

INSERT INTO [corez].[MstFasilitas] ([Jumlah], [CreatedBy], [CreatedDate], [ModifiedBy], [ModifiedDate], [FasilitasID], [NamaFasilitas], [RuanganID]) VALUES (N'2', N'1', N'2025-05-29 16:33:22.000000', NULL, NULL, N'F0011', N'Proyektor LCD', N'R004')
;

SET IDENTITY_INSERT [corez].[TxnPesan] ON;

INSERT INTO [corez].[TxnPesan] ([Berakhir], [Durasi], [Mulai], [TanggalPemesanan], [TanggalPertemuan], [ModifiedDate], [PesanID], [RuanganID], [Status], [UserID], [NamaPertemuan], [CreatedBy], [ModifiedBy]) VALUES (N'14:00:00.0000000', N'1.0', N'13:00:00.0000000', N'2025-06-20', N'2025-06-21', NULL, N'1', N'R001', N'Booked', N'Yng1998', N'Pertemuan 1', N'1', NULL)
;

INSERT INTO [corez].[TxnPesan] ([Berakhir], [Durasi], [Mulai], [TanggalPemesanan], [TanggalPertemuan], [ModifiedDate], [PesanID], [RuanganID], [Status], [UserID], [NamaPertemuan], [CreatedBy], [ModifiedBy]) VALUES (N'15:00:00.0000000', N'1.0', N'14:00:00.0000000', N'2025-06-20', N'2025-06-21', NULL, N'2', N'R002', N'Booked', N'paul.cumi', N'Pertemuan 2', N'1', NULL)
;

SET IDENTITY_INSERT [corez].[TxnPesan] OFF;

--INSERT INTO [corez].[TxnPesan] ([Berakhir], [Durasi], [Mulai], [TanggalPertemuan], [CreatedBy], [ModifiedBy], [ModifiedDate], [PesanID], [TanggalPemesanan], [RuanganID], [Status], [UserID], [NamaPertemuan]) VALUES (N'15:00:00.0000000', N'2.0', N'13:00:00.0000000', N'2025-05-30', N'1', NULL, NULL, N'1', N'2025-05-28 12:17:40.000000', N'RM0201', N'pending', N'Yng1998', N'Pertemaun Pertama')
--;