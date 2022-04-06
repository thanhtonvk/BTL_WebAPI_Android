namespace ModelsObject
{
    using System.Collections.Generic;


    public partial class Product
    {
        public int ID { get; set; }

        public string Username { get; set; }
        public string Name { get; set; }

        public int IDCategory { get; set; }

        public int IDBrand { get; set; }

        public int? Cost { get; set; }

        public double? Sale { get; set; }

        public int? FlashSaleFrom { get; set; }

        public int? FlashSaleTo { get; set; }
        public string Image { get; set; }


        public string Description { get; set; }


        public string Details { get; set; }

        public int? Quantity { get; set; }

        public bool? Status { get; set; }

      

    
    }
}