namespace TQMallWeb.Models
{

    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;

    public class Category
    {


        [Key]
        public int ID { get; set; }

        [Required]
        public string Name { get; set; }
        [Required]

        public string Image { get; set; }

        public bool? Status { get; set; }



    }
}
