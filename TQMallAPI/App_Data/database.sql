use master 
go
drop database if exists TQMall
go
create database TQMall
go
use TQMall
go
create table Account(
    Username nvarchar(50) primary key,
    Password varchar(50) not null,
    FullName nvarchar(100),
    Avatar ntext,
    DateOfBirth ntext,
    PhoneNumber char(10),
    Address ntext,
    Status smallint default 0,
    DataUser ntext
)
go
create table Category(
    ID int identity (1000,1) primary key ,
    Name ntext,
    Image ntext,
    Status bit default 0
)
go
create table Brand(
    ID int identity (1000,1) primary key ,
    Name ntext,
    Image ntext,
    Status bit default 0
)
go
create table Product(
    ID int identity (1000,1) primary key ,
    Name ntext,
    Username nvarchar(50) not null constraint fk_1 foreign key(Username) references Account(Username),
    IDCategory int not null constraint fk_2 foreign key(IDCategory) references Category(ID),
    IDBrand int not null constraint fk_3 foreign key(IDBrand) references Brand(ID),
    Cost int,
    Sale float,
    FlashSaleFrom int,
    FlashSaleTo int,
    Image ntext,
    Description ntext,
    Details ntext,
    Quantity int,
    Status bit default 0
)
go
create table ProductDetails(
    ID int identity (1000,1) primary key ,
    IDProduct int not null constraint fk_10 foreign key(IDProduct) references Product(ID),
    Name ntext,
    Cost int,
    Image ntext,
    Status bit default 0
)
go
create table ImageProduct(
    ID int identity (1000,1) primary key ,
    IDProduct int not null constraint fk_4 foreign key(IDProduct) references Product(ID),
    Image ntext,
    Status bit default 0
)
go
create table ReviewProduct(
    ID int identity (1000,1) primary key ,
    Username nvarchar(50) not null constraint fk_5 foreign key(Username) references Account(Username),
    IDProduct int not null constraint fk_6 foreign key(IDProduct) references Product(ID),
    Rate int,
    Review ntext,
    Image ntext
)
go
create table Voucher(
    ID char(20) primary key ,
    Username nvarchar(50) not null constraint fk_7 foreign key(Username) references Account(Username),
    Sale float,
    Quantity int,
    Status bit default 0
)
go
create table Cart(
    ID int identity (1000,1) primary key ,
    Username nvarchar(50) not null constraint fk_8 foreign key(Username) references Account(Username),
    IDProductDetails int not null constraint fk_9 foreign key(IDProductDetails) references ProductDetails(ID),
    Quantity int,
)
go
create table [Order](
    ID int identity (1000,1) primary key ,
    Username nvarchar(50) not null constraint fk_11 foreign key(Username) references Account(Username),
    Date ntext,
    PhoneNumber char(10),
    Address ntext,
    Status smallint
)
go
create table OrderDetails(
    ID int identity (1000,1) primary key ,
    IDOrder int not null constraint fk_12 foreign key(IDOrder) references [Order](ID),
    IDProductDetails int not null constraint fk_13 foreign key(IDProductDetails) references ProductDetails(ID),
    Quantity int not null
)
