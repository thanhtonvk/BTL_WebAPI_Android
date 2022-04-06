using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.ComponentModel.DataAnnotations;
namespace TQMallWeb.Models
{
    public  class Account
    {
        [Required]
        public string Username { get; set; }

        [Required]
        public string Password { get; set; }

        [Required]
        public string FullName { get; set; }

       
        public string Avatar { get; set; }
        [Required]

        public string DateOfBirth { get; set; }

        [Required]
        public string PhoneNumber { get; set; }

        [Required]
        public string Address { get; set; }

        public int? Status { get; set; }
        
        public string DataUser { get; set; }
        
        
    }
}
