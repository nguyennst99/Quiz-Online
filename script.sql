USE [master]
GO
/****** Object:  Database [QuizOnline]    Script Date: 6/2/2020 3:18:19 PM ******/
CREATE DATABASE [QuizOnline]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'QuizOnline', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.SQLEXPRESS\MSSQL\DATA\QuizOnline.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'QuizOnline_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.SQLEXPRESS\MSSQL\DATA\QuizOnline_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [QuizOnline] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [QuizOnline].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [QuizOnline] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [QuizOnline] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [QuizOnline] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [QuizOnline] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [QuizOnline] SET ARITHABORT OFF 
GO
ALTER DATABASE [QuizOnline] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [QuizOnline] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [QuizOnline] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [QuizOnline] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [QuizOnline] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [QuizOnline] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [QuizOnline] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [QuizOnline] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [QuizOnline] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [QuizOnline] SET  DISABLE_BROKER 
GO
ALTER DATABASE [QuizOnline] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [QuizOnline] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [QuizOnline] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [QuizOnline] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [QuizOnline] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [QuizOnline] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [QuizOnline] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [QuizOnline] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [QuizOnline] SET  MULTI_USER 
GO
ALTER DATABASE [QuizOnline] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [QuizOnline] SET DB_CHAINING OFF 
GO
ALTER DATABASE [QuizOnline] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [QuizOnline] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [QuizOnline] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [QuizOnline] SET QUERY_STORE = OFF
GO
USE [QuizOnline]
GO
/****** Object:  Table [dbo].[tbl_Question]    Script Date: 6/2/2020 3:18:19 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Question](
	[questionID] [int] IDENTITY(1,1) NOT NULL,
	[question_content] [varchar](500) NULL,
	[answerA] [varchar](500) NULL,
	[answerB] [varchar](500) NULL,
	[answerC] [varchar](500) NULL,
	[answerD] [varchar](500) NULL,
	[answer_correct] [varchar](500) NULL,
	[createDate] [date] NULL,
	[subjectID] [varchar](20) NULL,
	[statusID] [int] NULL,
 CONSTRAINT [PK_tbl_Question] PRIMARY KEY CLUSTERED 
(
	[questionID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_QuizTest]    Script Date: 6/2/2020 3:18:19 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_QuizTest](
	[idSoS] [int] IDENTITY(1,1) NOT NULL,
	[subjectID] [varchar](20) NULL,
	[email] [varchar](50) NULL,
	[Score] [float] NULL,
 CONSTRAINT [PK_tbl_SubjectOfStudent] PRIMARY KEY CLUSTERED 
(
	[idSoS] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_Role]    Script Date: 6/2/2020 3:18:19 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Role](
	[roleID] [int] NOT NULL,
	[roleName] [varchar](50) NULL,
 CONSTRAINT [PK_tbl_Role] PRIMARY KEY CLUSTERED 
(
	[roleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_Status]    Script Date: 6/2/2020 3:18:19 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Status](
	[statusID] [int] NOT NULL,
	[statusName] [varchar](50) NULL,
 CONSTRAINT [PK_tbl_Status] PRIMARY KEY CLUSTERED 
(
	[statusID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_Subject]    Script Date: 6/2/2020 3:18:19 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Subject](
	[subjectID] [varchar](20) NOT NULL,
	[subjectName] [varchar](50) NULL,
	[numberOfQuestion] [int] NULL,
	[time] [int] NULL,
 CONSTRAINT [PK_tbl_Subject] PRIMARY KEY CLUSTERED 
(
	[subjectID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_User]    Script Date: 6/2/2020 3:18:19 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_User](
	[email] [varchar](50) NOT NULL,
	[name] [varchar](50) NULL,
	[password] [varchar](5000) NULL,
	[roleID] [int] NULL,
	[statusID] [int] NULL,
 CONSTRAINT [PK_tbl_User] PRIMARY KEY CLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[tbl_Question] ON 

INSERT [dbo].[tbl_Question] ([questionID], [question_content], [answerA], [answerB], [answerC], [answerD], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (1, N'1+1=', N'1', N'2', N'3', N'4', N'B', NULL, N'M01', 3)
INSERT [dbo].[tbl_Question] ([questionID], [question_content], [answerA], [answerB], [answerC], [answerD], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (2, N'1+2=', N'1', N'2', N'3', N'4', N'C', NULL, N'M01', 2)
INSERT [dbo].[tbl_Question] ([questionID], [question_content], [answerA], [answerB], [answerC], [answerD], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (3, N'2+1=', N'1', N'2', N'3', N'4', N'C', NULL, N'M01', 2)
INSERT [dbo].[tbl_Question] ([questionID], [question_content], [answerA], [answerB], [answerC], [answerD], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (4, N'2+2=', N'1 ', N'2', N'3', N'4', N'D', NULL, N'M01', 2)
INSERT [dbo].[tbl_Question] ([questionID], [question_content], [answerA], [answerB], [answerC], [answerD], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (5, N'1+3=', N'1', N'2', N'3', N'4', N'D', NULL, N'M01', 2)
INSERT [dbo].[tbl_Question] ([questionID], [question_content], [answerA], [answerB], [answerC], [answerD], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (6, N'2+3=', N'3', N'4', N'5', N'6', N'C', NULL, N'M01', 2)
INSERT [dbo].[tbl_Question] ([questionID], [question_content], [answerA], [answerB], [answerC], [answerD], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (7, N'1+4=', N'3', N'4', N'5', N'6', N'C', NULL, N'M01', 2)
INSERT [dbo].[tbl_Question] ([questionID], [question_content], [answerA], [answerB], [answerC], [answerD], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (8, N'2+4=', N'3', N'4', N'5', N'6', N'D', NULL, N'M01', 2)
INSERT [dbo].[tbl_Question] ([questionID], [question_content], [answerA], [answerB], [answerC], [answerD], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (9, N'1+5=', N'3', N'5', N'6', N'4', N'C', NULL, N'M01', 2)
INSERT [dbo].[tbl_Question] ([questionID], [question_content], [answerA], [answerB], [answerC], [answerD], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (10, N'1+6=', N'5', N'6', N'7', N'8', N'C', NULL, N'M01', 2)
INSERT [dbo].[tbl_Question] ([questionID], [question_content], [answerA], [answerB], [answerC], [answerD], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (11, N'1+7=', N'5', N'6', N'7', N'8', N'D', NULL, N'M01', 2)
INSERT [dbo].[tbl_Question] ([questionID], [question_content], [answerA], [answerB], [answerC], [answerD], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (12, N'1+8=', N'6', N'7', N'8', N'9', N'D', NULL, N'M01', 2)
INSERT [dbo].[tbl_Question] ([questionID], [question_content], [answerA], [answerB], [answerC], [answerD], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (13, N'1+9=', N'4', N'6', N'8', N'10', N'D', NULL, N'M01', 2)
INSERT [dbo].[tbl_Question] ([questionID], [question_content], [answerA], [answerB], [answerC], [answerD], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (14, N'2+4=', N'4', N'6', N'8', N'9', N'B', NULL, N'M01', 3)
INSERT [dbo].[tbl_Question] ([questionID], [question_content], [answerA], [answerB], [answerC], [answerD], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (15, N'2+5=', N'7', N'3', N'8', N'6', N'A', NULL, N'M01', 2)
INSERT [dbo].[tbl_Question] ([questionID], [question_content], [answerA], [answerB], [answerC], [answerD], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (16, N'2+6=', N'8', N'3', N'7', N'3', N'A', NULL, N'M01', 2)
INSERT [dbo].[tbl_Question] ([questionID], [question_content], [answerA], [answerB], [answerC], [answerD], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (17, N'2+7=', N'6', N'7', N'8', N'9', N'D', NULL, N'M01', 2)
INSERT [dbo].[tbl_Question] ([questionID], [question_content], [answerA], [answerB], [answerC], [answerD], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (18, N'2+8=', N'10', N'3', N'5', N'4', N'A', NULL, N'M01', 2)
INSERT [dbo].[tbl_Question] ([questionID], [question_content], [answerA], [answerB], [answerC], [answerD], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (19, N'2+9=', N'10', N'11', N'12', N'13', N'B', NULL, N'M01', 2)
INSERT [dbo].[tbl_Question] ([questionID], [question_content], [answerA], [answerB], [answerC], [answerD], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (20, N'3+3=', N'5', N'6', N'7', N'8', N'B', NULL, N'M01', 2)
INSERT [dbo].[tbl_Question] ([questionID], [question_content], [answerA], [answerB], [answerC], [answerD], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (21, N'3+4=', N'5', N'6', N'7', N'8', N'C', NULL, N'M01', 2)
INSERT [dbo].[tbl_Question] ([questionID], [question_content], [answerA], [answerB], [answerC], [answerD], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (22, N'2+5=', N'6', N'7', N'8', N'9', N'B', NULL, N'M01', 2)
INSERT [dbo].[tbl_Question] ([questionID], [question_content], [answerA], [answerB], [answerC], [answerD], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (23, N'3+5=', N'5', N'6', N'7', N'8', N'D', NULL, N'M01', 2)
INSERT [dbo].[tbl_Question] ([questionID], [question_content], [answerA], [answerB], [answerC], [answerD], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (24, N'3+6=', N'9', N'8', N'7', N'6', N'A', NULL, N'M01', 2)
INSERT [dbo].[tbl_Question] ([questionID], [question_content], [answerA], [answerB], [answerC], [answerD], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (25, N'3+7=', N'10', N'9', N'8', N'7', N'A', NULL, N'M01', 2)
INSERT [dbo].[tbl_Question] ([questionID], [question_content], [answerA], [answerB], [answerC], [answerD], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (26, N'3+8=', N'10', N'11', N'12', N'13', N'B', NULL, N'M01', 2)
INSERT [dbo].[tbl_Question] ([questionID], [question_content], [answerA], [answerB], [answerC], [answerD], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (27, N'3+9=', N'10', N'11', N'12', N'13', N'C', NULL, N'M01', 2)
INSERT [dbo].[tbl_Question] ([questionID], [question_content], [answerA], [answerB], [answerC], [answerD], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (28, N'4+4=', N'6', N'7', N'9', N'8', N'D', NULL, N'M01', 2)
INSERT [dbo].[tbl_Question] ([questionID], [question_content], [answerA], [answerB], [answerC], [answerD], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (29, N'4+5=', N'6', N'7', N'8', N'9', N'D', NULL, N'M01', 2)
INSERT [dbo].[tbl_Question] ([questionID], [question_content], [answerA], [answerB], [answerC], [answerD], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (30, N'4+6=', N'10', N'9', N'8', N'7', N'A', NULL, N'M01', 2)
INSERT [dbo].[tbl_Question] ([questionID], [question_content], [answerA], [answerB], [answerC], [answerD], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (31, N'4+7=', N'10', N'11', N'12', N'13', N'B', NULL, N'M01', 2)
INSERT [dbo].[tbl_Question] ([questionID], [question_content], [answerA], [answerB], [answerC], [answerD], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (32, N'2+1=', N'5', N'6', N'3', N'2', N'C', NULL, N'M01', 2)
INSERT [dbo].[tbl_Question] ([questionID], [question_content], [answerA], [answerB], [answerC], [answerD], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (33, N'3+1=', N'2', N'3', N'4', N'5', N'C', NULL, N'M01', 2)
INSERT [dbo].[tbl_Question] ([questionID], [question_content], [answerA], [answerB], [answerC], [answerD], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (34, N'4+1=', N'5', N'2', N'3', N'4', N'A', NULL, N'M01', 2)
INSERT [dbo].[tbl_Question] ([questionID], [question_content], [answerA], [answerB], [answerC], [answerD], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (35, N'5+1=', N'5', N'6', N'7', N'8', N'B', NULL, N'M01', 2)
INSERT [dbo].[tbl_Question] ([questionID], [question_content], [answerA], [answerB], [answerC], [answerD], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (36, N'1+1+1=', N'1', N'2', N'3', N'4', N'C', NULL, N'M01', 3)
INSERT [dbo].[tbl_Question] ([questionID], [question_content], [answerA], [answerB], [answerC], [answerD], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (37, N'1+1+2=', N'5', N'2', N'3', N'4', N'D', NULL, N'M01', 3)
INSERT [dbo].[tbl_Question] ([questionID], [question_content], [answerA], [answerB], [answerC], [answerD], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (38, N'1+1+3=', N'1', N'2', N'3', N'5', N'D', NULL, N'M01', 2)
INSERT [dbo].[tbl_Question] ([questionID], [question_content], [answerA], [answerB], [answerC], [answerD], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (39, N'1+2+3=', N'6', N'5', N'4', N'3', N'A', NULL, N'M01', 2)
INSERT [dbo].[tbl_Question] ([questionID], [question_content], [answerA], [answerB], [answerC], [answerD], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (40, N'1+2+4=', N'6', N'7', N'8', N'9', N'B', NULL, N'M01', 2)
INSERT [dbo].[tbl_Question] ([questionID], [question_content], [answerA], [answerB], [answerC], [answerD], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (41, N'1+1+4=', N'4', N'6', N'7', N'8', N'B', NULL, N'M01', 3)
INSERT [dbo].[tbl_Question] ([questionID], [question_content], [answerA], [answerB], [answerC], [answerD], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (42, N'1+4+2=', N'5', N'6', N'7', N'8', N'C', NULL, N'M02', 2)
INSERT [dbo].[tbl_Question] ([questionID], [question_content], [answerA], [answerB], [answerC], [answerD], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (43, N'1+3+4=', N'5', N'6', N'7', N'8', N'D', NULL, N'M01', 2)
INSERT [dbo].[tbl_Question] ([questionID], [question_content], [answerA], [answerB], [answerC], [answerD], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (44, N'4+9=', N'10', N'11', N'12', N'13', N'D', CAST(N'2020-05-27' AS Date), N'M01', 2)
INSERT [dbo].[tbl_Question] ([questionID], [question_content], [answerA], [answerB], [answerC], [answerD], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (45, N'4+9=', N'10', N'11', N'12', N'13', N'D', CAST(N'2020-05-27' AS Date), N'M03', 2)
INSERT [dbo].[tbl_Question] ([questionID], [question_content], [answerA], [answerB], [answerC], [answerD], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (47, N'4-2=', N'1', N'2', N'3', N'5', N'B', CAST(N'2020-05-28' AS Date), N'M02', 2)
INSERT [dbo].[tbl_Question] ([questionID], [question_content], [answerA], [answerB], [answerC], [answerD], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (48, N'1+5+6=', N'10', N'11', N'13', N'12', N'D', CAST(N'2020-06-01' AS Date), N'M01', 2)
INSERT [dbo].[tbl_Question] ([questionID], [question_content], [answerA], [answerB], [answerC], [answerD], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (49, N'1+5+7=', N'10', N'11', N'12', N'13', N'D', CAST(N'2020-06-01' AS Date), N'M01', 2)
INSERT [dbo].[tbl_Question] ([questionID], [question_content], [answerA], [answerB], [answerC], [answerD], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (50, N'1+1+1+1=', N'1', N'2', N'3', N'4', N'D', CAST(N'2020-06-01' AS Date), N'M01', 2)
INSERT [dbo].[tbl_Question] ([questionID], [question_content], [answerA], [answerB], [answerC], [answerD], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (51, N'1+5+4=', N'8', N'9', N'10', N'11', N'C', CAST(N'2020-06-01' AS Date), N'M01', 2)
INSERT [dbo].[tbl_Question] ([questionID], [question_content], [answerA], [answerB], [answerC], [answerD], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (52, N'1+2+3+4=', N'7', N'8', N'9', N'10', N'D', CAST(N'2020-06-02' AS Date), N'M01', 2)
SET IDENTITY_INSERT [dbo].[tbl_Question] OFF
SET IDENTITY_INSERT [dbo].[tbl_QuizTest] ON 

INSERT [dbo].[tbl_QuizTest] ([idSoS], [subjectID], [email], [Score]) VALUES (1, N'M01', N'student1@gmail.com', NULL)
INSERT [dbo].[tbl_QuizTest] ([idSoS], [subjectID], [email], [Score]) VALUES (2, N'M01', N'student1@gmail.com', 0.25)
INSERT [dbo].[tbl_QuizTest] ([idSoS], [subjectID], [email], [Score]) VALUES (3, N'M01', N'student1@gmail.com', 0.25)
INSERT [dbo].[tbl_QuizTest] ([idSoS], [subjectID], [email], [Score]) VALUES (4, N'M01', N'student1@gmail.com', 0.25)
INSERT [dbo].[tbl_QuizTest] ([idSoS], [subjectID], [email], [Score]) VALUES (5, N'M01', N'student1@gmail.com', 0.25)
INSERT [dbo].[tbl_QuizTest] ([idSoS], [subjectID], [email], [Score]) VALUES (6, N'M01', N'student1@gmail.com', 0)
INSERT [dbo].[tbl_QuizTest] ([idSoS], [subjectID], [email], [Score]) VALUES (7, N'M01', N'student1@gmail.com', 0.25)
INSERT [dbo].[tbl_QuizTest] ([idSoS], [subjectID], [email], [Score]) VALUES (8, N'M01', N'student1@gmail.com', 0.5)
INSERT [dbo].[tbl_QuizTest] ([idSoS], [subjectID], [email], [Score]) VALUES (9, N'M01', N'student1@gmail.com', 0.75)
SET IDENTITY_INSERT [dbo].[tbl_QuizTest] OFF
INSERT [dbo].[tbl_Role] ([roleID], [roleName]) VALUES (1, N'admin')
INSERT [dbo].[tbl_Role] ([roleID], [roleName]) VALUES (2, N'student')
INSERT [dbo].[tbl_Status] ([statusID], [statusName]) VALUES (1, N'New')
INSERT [dbo].[tbl_Status] ([statusID], [statusName]) VALUES (2, N'active')
INSERT [dbo].[tbl_Status] ([statusID], [statusName]) VALUES (3, N'deActive')
INSERT [dbo].[tbl_Subject] ([subjectID], [subjectName], [numberOfQuestion], [time]) VALUES (N'M01', N'Math1', 40, 50)
INSERT [dbo].[tbl_Subject] ([subjectID], [subjectName], [numberOfQuestion], [time]) VALUES (N'M02', N'Math2', 50, 60)
INSERT [dbo].[tbl_Subject] ([subjectID], [subjectName], [numberOfQuestion], [time]) VALUES (N'M03', N'Math3', 50, 60)
INSERT [dbo].[tbl_Subject] ([subjectID], [subjectName], [numberOfQuestion], [time]) VALUES (N'M04', N'Math4', 50, 60)
INSERT [dbo].[tbl_User] ([email], [name], [password], [roleID], [statusID]) VALUES (N'12312312@gmail.com', N'12312312', N'96cae35ce8a9b0244178bf28e4966c2ce1b8385723a96a6b838858cdd6ca0a1e', 2, 1)
INSERT [dbo].[tbl_User] ([email], [name], [password], [roleID], [statusID]) VALUES (N'admin@gmail.com', N'123123', N'96cae35ce8a9b0244178bf28e4966c2ce1b8385723a96a6b838858cdd6ca0a1e', 2, 3)
INSERT [dbo].[tbl_User] ([email], [name], [password], [roleID], [statusID]) VALUES (N'nguyennst@gmail.com', N'Nguyen Si Trieu Nguyen', N'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 1, 2)
INSERT [dbo].[tbl_User] ([email], [name], [password], [roleID], [statusID]) VALUES (N'student1@gmail.com', N'Student1', N'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 2, 2)
INSERT [dbo].[tbl_User] ([email], [name], [password], [roleID], [statusID]) VALUES (N'student2@gmail.com', N'Student 2', N'1', 2, 2)
INSERT [dbo].[tbl_User] ([email], [name], [password], [roleID], [statusID]) VALUES (N'student5@gmail.com', N'Nguyen', N'123123', 2, 1)
INSERT [dbo].[tbl_User] ([email], [name], [password], [roleID], [statusID]) VALUES (N'student7@gmail.com', N'123', N'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 2, 1)
INSERT [dbo].[tbl_User] ([email], [name], [password], [roleID], [statusID]) VALUES (N'user@gmail.com', N'User Demo', N'1', 2, 2)
ALTER TABLE [dbo].[tbl_Question]  WITH CHECK ADD  CONSTRAINT [FK_tbl_Question_tbl_Status] FOREIGN KEY([statusID])
REFERENCES [dbo].[tbl_Status] ([statusID])
GO
ALTER TABLE [dbo].[tbl_Question] CHECK CONSTRAINT [FK_tbl_Question_tbl_Status]
GO
ALTER TABLE [dbo].[tbl_Question]  WITH CHECK ADD  CONSTRAINT [FK_tbl_Question_tbl_Subject] FOREIGN KEY([subjectID])
REFERENCES [dbo].[tbl_Subject] ([subjectID])
GO
ALTER TABLE [dbo].[tbl_Question] CHECK CONSTRAINT [FK_tbl_Question_tbl_Subject]
GO
ALTER TABLE [dbo].[tbl_QuizTest]  WITH CHECK ADD  CONSTRAINT [FK_tbl_QuizTest_tbl_Subject] FOREIGN KEY([subjectID])
REFERENCES [dbo].[tbl_Subject] ([subjectID])
GO
ALTER TABLE [dbo].[tbl_QuizTest] CHECK CONSTRAINT [FK_tbl_QuizTest_tbl_Subject]
GO
ALTER TABLE [dbo].[tbl_QuizTest]  WITH CHECK ADD  CONSTRAINT [FK_tbl_QuizTest_tbl_User] FOREIGN KEY([email])
REFERENCES [dbo].[tbl_User] ([email])
GO
ALTER TABLE [dbo].[tbl_QuizTest] CHECK CONSTRAINT [FK_tbl_QuizTest_tbl_User]
GO
ALTER TABLE [dbo].[tbl_User]  WITH CHECK ADD  CONSTRAINT [FK_tbl_User_tbl_Role] FOREIGN KEY([roleID])
REFERENCES [dbo].[tbl_Role] ([roleID])
GO
ALTER TABLE [dbo].[tbl_User] CHECK CONSTRAINT [FK_tbl_User_tbl_Role]
GO
ALTER TABLE [dbo].[tbl_User]  WITH CHECK ADD  CONSTRAINT [FK_tbl_User_tbl_Status] FOREIGN KEY([statusID])
REFERENCES [dbo].[tbl_Status] ([statusID])
GO
ALTER TABLE [dbo].[tbl_User] CHECK CONSTRAINT [FK_tbl_User_tbl_Status]
GO
USE [master]
GO
ALTER DATABASE [QuizOnline] SET  READ_WRITE 
GO
