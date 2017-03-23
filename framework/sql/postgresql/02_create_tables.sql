USE "framework";

-- SYSTEM INFORMATION

DROP TABLE IF EXISTS "SystemInformation";
CREATE TABLE "SystemInformation" (
  "Id" SERIAL NOT NULL PRIMARY KEY UNIQUE,
  "PropertyName" text NOT NULL,
  "PropertyValue" text NOT NULL,
  "TimestampOfRecord" timestamp with time zone NOT NULL
)
WITH (
  OIDS = FALSE
);

-- USER --

DROP TABLE IF EXISTS "User";
CREATE TABLE "User" (
  "Id" SERIAL NOT NULL PRIMARY KEY UNIQUE,
  "UserName" text NOT NULL UNIQUE,
  "Password" text NOT NULL UNIQUE,
  "Salt" text NOT NULL UNIQUE,
  "Email" text NOT NULL UNIQUE,
  "Status" char(1) NOT NULL,
  "ActivationToken" text NOT NULL UNIQUE,
  "Language" char(2) NOT NULL,
  "Version" integer NOT NULL 
)
WITH (
  OIDS = FALSE
);

DROP TABLE IF EXISTS "UserHistory";
CREATE TABLE "UserHistory" (
  "Id" SERIAL NOT NULL PRIMARY KEY UNIQUE,
  "UserName" text NOT NULL,
  "Email" text NOT NULL,
  "Status" char(1) NOT NULL,
  "Language" char(2) NOT NULL,
  "TimestampOfChange" timestamp with time zone NOT NULL
)
WITH (
  OIDS = FALSE
);

-- USER ROLES --

DROP TABLE IF EXISTS "UserRoles";
CREATE TABLE "UserRoles" (
  "Id" SERIAL NOT NULL PRIMARY KEY UNIQUE, 
  "UserName" text NOT NULL, 
  "Role" text NOT NULL
)
WITH (
  OIDS = FALSE
);

DROP TABLE IF EXISTS "UserRolesHistory";
CREATE TABLE "UserRolesHistory" (
  "Id" SERIAL NOT NULL PRIMARY KEY UNIQUE, 
  "UserName" text NOT NULL, 
  "Role" text NOT NULL,
  "TimestampOfChange" timestamp with time zone NOT NULL
)
WITH (
  OIDS = FALSE
);

-- ROLE PERMISSIONS --

DROP TABLE IF EXISTS "RolePermissions";
CREATE TABLE "RolePermissions" (
  "Id" SERIAL NOT NULL PRIMARY KEY UNIQUE, 
  "Permission" text NOT NULL, 
  "Role" text NOT NULL
)
WITH (
  OIDS = FALSE
);

DROP TABLE IF EXISTS "RolePermissionsHistory";
CREATE TABLE "RolePermissionsHistory" (
  "Id" SERIAL NOT NULL PRIMARY KEY UNIQUE, 
  "Permission" text NOT NULL, 
  "Role" text NOT NULL,
  "TimestampOfChange" timestamp with time zone NOT NULL
)
WITH (
  OIDS = FALSE
);

-- COUNTRY --

DROP TABLE IF EXISTS "Country";
CREATE TABLE "Country" (
  "Id" SERIAL NOT NULL PRIMARY KEY UNIQUE,
  "Alpha2" char(2) NOT NULL,
  "Alpha3" char(3) NOT NULL,
  "CountryCode" integer NOT NULL,
  "CountryNameEN" text NULL,
  "CountryNameSK" text NULL,
  "CountryNameOriginal" text NULL,
  "Region" text NULL,
  "RegionCode" integer NULL, 
  "SubRegion" text NULL,
  "SubRegionCode" integer NULL
)
WITH (
  OIDS = FALSE
);
