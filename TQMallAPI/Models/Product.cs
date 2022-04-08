//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated from a template.
//
//     Manual changes to this file may cause unexpected behavior in your application.
//     Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace TQMallAPI.Models
{
    using System;
    using System.Collections.Generic;
    
    public partial class Product
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public Product()
        {
            this.ImageProducts = new HashSet<ImageProduct>();
            this.ProductDetails = new HashSet<ProductDetail>();
            this.ReviewProducts = new HashSet<ReviewProduct>();
        }
    
        public int ID { get; set; }
        public string Name { get; set; }
        public string Username { get; set; }
        public int IDCategory { get; set; }
        public int IDBrand { get; set; }
        public Nullable<int> Cost { get; set; }
        public Nullable<double> Sale { get; set; }
        public Nullable<int> FlashSaleFrom { get; set; }
        public Nullable<int> FlashSaleTo { get; set; }
        public string Image { get; set; }
        public string Description { get; set; }
        public string Details { get; set; }
        public Nullable<int> Quantity { get; set; }
        public Nullable<bool> Status { get; set; }
    
        public virtual Account Account { get; set; }
        public virtual Brand Brand { get; set; }
        public virtual Category Category { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<ImageProduct> ImageProducts { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<ProductDetail> ProductDetails { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<ReviewProduct> ReviewProducts { get; set; }
    }
}
