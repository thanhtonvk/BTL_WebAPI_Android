using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;


namespace DataAccessLayer
{
    internal class Program
    {
        public static void Main(string[] args)
        {
            AccountDAL accountDal = new AccountDAL();
            foreach (var item in accountDal.GetAccountList())
            {
                Console.WriteLine(item.Username);
            }
            Console.WriteLine(accountDal.GetAccountList().Count());

            Console.ReadKey();
        }
    }
}
