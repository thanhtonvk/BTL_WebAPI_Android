namespace TQMallWeb.Models
{
    using DataAccessLayer;
    using System;
    using System.Collections.Generic;
    using System.ComponentModel;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Web.Mvc;

    public partial class Product
    {
        public int ID { get; set; }
        [DisplayName("Tên sản phẩm")]
        [Required]
        public string Name { get; set; }
        public string Username { get; set; }
        [DisplayName("Danh mục")]
        [Required]
        public int IDCategory { get; set; }
        [DisplayName("Hãng sản xuất")]
        [Required]
        public int IDBrand { get; set; }
        [Range(1,int.MaxValue)]
        [DisplayName("Giá bán")]
        [Required]
        public Nullable<int> Cost { get; set; }
        [DisplayName("Sale")]
        [Range(0.0,1.0)]
        public Nullable<double> Sale { get; set; }
        [Range(1,24)]
        [DisplayName("Flash sale từ")]
        public Nullable<int> FlashSaleFrom { get; set; }
        [Range(1, 24)]
        [DisplayName("Flash sale đến")]
        public Nullable<int> FlashSaleTo { get; set; }
        [DisplayName("Hình ảnh")]
        [Required]
        public string Image { get; set; }


        [AllowHtml]
        [DisplayName("Mô tả sản phẩm")]
        [Required]
        public string Description { get; set; }


        [Required]
        [AllowHtml]
        [DisplayName("Chi tiết sản phẩm")]
        public string Details { get; set; }



        [DisplayName("Số lượng")]
        [Range(0,int.MaxValue)]
        [Required]
        public Nullable<int> Quantity { get; set; }

        public Nullable<bool> Status { get; set; }
      
        public  Category GetCategory()
        {
            CategoryDAL categoryDAL = new CategoryDAL();
            return categoryDAL.GetCategoryById(IDCategory);
        }
      
        public  Brand GetBrand()
        {
            BrandDAL brandDAL = new BrandDAL();
            return brandDAL.GetBrandById(IDBrand);
        }

    }
}