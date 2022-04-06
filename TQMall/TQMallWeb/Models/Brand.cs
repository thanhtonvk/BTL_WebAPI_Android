namespace TQMallWeb.Models
{
  
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;

    public  class Brand
    {
        
        [Key]
        public int ID { get; set; }
        
        public string Name { get; set; }

        public string Image { get; set; }

        public bool? Status { get; set; }
        
      
    }
}
