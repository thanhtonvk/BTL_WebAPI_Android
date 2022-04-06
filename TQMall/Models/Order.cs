namespace ModelsObject
{
    
    using System.Collections.Generic;
    
    public  class Order
    {

        public int ID { get; set; }

        public string Username { get; set; }

      
        public string Date { get; set; }

      
        public string PhoneNumber { get; set; }

      
        public string Address { get; set; }

        public short? Status { get; set; }

        public  Account Account { get; set; }

      
      
    }
}
