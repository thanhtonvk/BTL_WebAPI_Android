using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Web.Http;
using TQMallAPI.Models;

namespace TQMallAPI.Controllers
{
// chúa sẽ giúp bạn hết bug oke! 
// *,..,,..  .  ........  .                                                                                .. ... ....................,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,******,,,,,********,*
// *,,.,.....      ..... ...                                                                                   ..  ........................,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,****,,,****
// **,,,,,....   . ..     ...      .                                                                               .........................,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,*******
// *****,,,...... . ..      ..  ..  ...                                                                               ........................,..,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,...,,,*********
// *******,.......     . .   ...........                                                                               .............................,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,.....,,,,,,,,,,******
// ********,,......  .... ..  .......... ..                                                                             ...............................,,,,,,,,,,,,,,,,,,,,,,,,,,,,,.....,,,,,,,,,,,,******
// **********,,...   ..  ...............                                                                                 ...................................,,,,,,,,,,,,,,,,,,,,,......,,,,,,,,,,,,,,,*****
// ***********,,..  .... .................                                                                                 ....................................,,,,,,,,,,,,,,,........,,,,,,,,,,,,,,,,,,***
// ***********,,,,.........................                                                                                ........................................,,,,,,,,.........,,,,,,,,,,,,,,,,,,,,,,*
// ********,,,,,,,,,,,.....................  .                                                                              ..........................................................,,,,,,,,,,,,,,,,,,,,,
// ****,,,,,,,,,,,,,,,,,,........................                                                                            .......................................................,,,,%%#/,,,,,**,,,,,,,,
// ***,,,,,,,,,,,,,,,,,,,,,.............. . ......                                                                            ....................................................,#%#,,#%&%/,,.%%#,,,,,,,,
// *,,,,,,,,,,,,,,,,/*,,,,,,,,.............. ........                                                                          ....................................................#%%%, &%%%,,%%%%,,,,,,,,
// ,,,,,,,,,,,,,,,,(#%%.,,,,,,,...==...........      ...                                            #.%(##                         ................................................###%%%*.%%%%##%%%/,,,,,,,,
// ,,,,,,,,,(#%%,,*%%%%/#%(,,,,,...............                                             (#%%%&&&&&&&&%%%                      . ............................................(#%%%%#%,#%%%%%%&%,,,,,,,,,
// ,,,,,,,,,/%%%%,/%%%&,%%# ,,,.,.................                                         #%%&&&%%((((#&&@&%                        ...........................................(%%##%%%%&&%%%&&%,,,,,,,,,,
// ,,,,,,,,,,%&%%#(%%%%.%%%.,,*(#...................                                      #%&&#/////////(%%&&%                         ........................................./#%%&%%%%&&%%%&%,,,,,,,,,,,
// ,,,,,,,,,,,%%%%%%%%%#####,.###*...................                                    %%&(/////*/*/(((//#&%#                        ......................................... #%%%&%%%%&%%%%,,,,,,,,,,,,
// ,,,,,,,,,,,,%&%&%%%&%#%%% .###....................                                   ,&&%(%/#%%((//##%%%//@/,                         ....................................... ##%%%%%%%%%%%,,,,,,,,,,,,,
// ,,,,,,,,,,,,,*%&%%%%&%%%%%*###*.................. .                                  ,#&%((//*////*/(////(@*,                          ........................ ............ ###%%%%%%%%%%,,,,,,,,,,,,,,
// ,,,,,,,,,,,,,,,%&#%%&%%%%&%%%#/..................  .                                ,,,&%(//////#((%#////(%,,*                           ...................     ........ #((###%%%%%%%#.,,,,,,,,,,,,,,,
// ,,,,,,,,,,,,,,,,##%%%%%%%%#%##(..................                                  ,,,,@&#///#&&((/(#&&(/#@*,**                            .............        .......  #((###%%%%%%%*..,,,,,,,,,,,,,,,
// ,,,,,,,,,,,,,,,,,.%%%%%%%%####((..................                                ,*,,,&@&%#(%#(/(#(((%%#&&,,***,                          ............         .....  ,*(####%%%%%%%.......,,,,,,,,,,,,
// ,,,,,,,,,,,,,,.....,%#%%%%%%%###*...................                             ,,**,,*&@@&%%%%#%%&#%&@&@(,,****,                           ........           ....,,***%###%%%%%%* /,........,,,,,,,,,
// ,,,,,,,,,,,,,..,,....##%%%%%%%###(. .................                           ,,,**,,*@@@@&@&&@@&&@@@@@@**,*****,                          .. ....               ,,**/*/%%%%%%%%*  /*...........,,,,,,
// ,,,,,,,,,,,,,.........,#%#%%%%####(/* ...............                          ,,****,,*@@%&@@@@@@@@@@@@@&*,,******,                            .                 ,,,****,%%%%%%%,*, /* ...........,,,,,
// ,,,,,,,,,..............%%%%%%%%%#%#%*  ................                        ,,****,,,#@#%%&@&%&%%&&&%&&*,,*******,                                            ,******/*(%%%&%/  ,,(*.............,,,,
// ,,,,,,,,...............#%&%%%%%%%%%%*/, ...............                       ,,,****,,,*.#%%%#%#######%&&*,*********,                                           ,/****/***&%%#     ///,................
// ,,,,,,,................(((&%%%%%%%%&*/*,...............                      ,,,*****,,**.%%%%####(####%&&*,*****/****,**,                                     ,.,/***//****%/     /*/*.................
// ,,,,,,.................//( ,%%%%%%%#*//* .............                      ,,,,,**/,,,**%%%%%%%#####%%&&&,,***************,,,,                            ., ,***/*////***//**   */*.....  ...  .......
// ,,,,..................*/**(.(&&%%%%***(//   ..........                    ,,,,,,,*//,,,,///****#%&&%/***//,,***********,,*//*,*,,,,,                 ****,,(*****/(*////*****/////**...   .. ...........
// ,,,...................,(*/*/,.&&&&%***#(*, ** * .....                ,,,,*,,,,,,,///,,,*(*****,%,%%%********************,**(*,,****,/****,,,     .*********//*/**/#//////****////*....  . . .... . .....
// ,,....................,//*/// ,%&///*(///*/***,*** ....         ,,,,***,*,,*,,,,*//*,,**(/****,,##%%******,*****************#*,***,//*******,,*,****//***/**(//*/*/((((////****** ..    ................
// .,.....................*///*//,(*//*(///**//********. .      .,,*****,***,,*,,,*/(/*,***/(*,,*,,,*/,,*,*********************/%#*****///***********////**//**/(//*//*((((((////*....    .................
// .........................**////////(/**//*//////*///* (, ,,,,,,,******/(********#/*****//**,,,*,,**,,***/********************/%(***////****/*/***////**///**/#(///**//(((///*................ ..........
// ..........................,**//*///***///////////////*****************/*********(******/**,,,,,,,***,*,***************/*****//((***/**//***/****//////////**//%(///****((/*,............................
// ...........................,,*///****//////////////(////************/*(*********(/****/**,,,,,,,,***,,**/********************//#/***////*//***/////////////**/(%(////***/,..............................
// ..........................  .,,****////////////////(//*///*/////////*(**********#/****/**,,,,,,,,,**,***(*************/*******/#/***////*/****//////////////*//((///////*.    ..........................
// ............................ ..****////////////////(////////*//((////#**********#/********,,,*,,*,**,,*/(****/*********/*****//(#//**///******///(/(///////////(%(////////*   .. .......................
// ..............................**////////////////////(///////////(##(//*//******/#//********************/#****//***/***///***////(#/*//*/////*///////////////////(%#////////*  .  .......................
// .............................,**////////////////////(/////////////(#&////***/**/%//**(********,*********#*****//****//*//***/////((/(((/(/////////((////////////(#%(/////////    .......................
// ............................**//////////////////////((////////(//(#&(///***/*/*/%//*/(******************#/***/////*//////***//////(%%#(///////////(//////(///////(&&//////////, ........................
// ...........................*/////////////////////////(/////((((((/(%////////////#////(******************#//*/*////////////**///////(#%%//////(////(/////(/////////#&#((/////////........................
// ..........................*//////////////////////////(//////////##%#///////////(%(////******************(/***///////////////////////(%%#((////////(////(/((///////(%%((//////////.......................
// ....................... .////////////////////////////((/////////(#%////////////(%(//#/*****/************(//*/////////////////////////(###((////////////((((////////(%%#((/(///////......................
    public class CartController : ApiController
    {
        private DBContext _dbContext = new DBContext();

        [HttpGet]
        [Route("api/cart/getcarbyuser")]
        public IEnumerable<Cart> GetCartByUser(string username)
        {
            List<Cart> carts = new List<Cart>();
            var model = _dbContext.Carts.Where(x => x.Username.Equals(username));
            foreach (var item in model)
            {
                Cart cart = new Cart()
                {
                    ID = item.ID,
                    IDProductDetails = item.IDProductDetails,
                    Quantity = item.Quantity,
                    Username = item.Username
                };
                carts.Add(cart);
            }

            return carts;
        }

        [HttpPut, HttpPost]
        [Route("api/cart/addcart")]
        public int AddCart([FromBody] Cart cart)
        {
            int index = IsExist(cart.IDProductDetails);
            if (index > -1)
            {
                var model = _dbContext.Carts.ToList()[index];
                _dbContext.Carts.Add(model);
                _dbContext.Entry(model).State = EntityState.Modified;
            }
            else
            {
                _dbContext.Carts.Add(cart);
            }

            return _dbContext.SaveChanges();
        }

        [HttpPut]
        [Route("api/cart/updatecart")]
        public int UpdateCart(int id, int quantity)
        {
            var model = _dbContext.Carts.Find(id);
            if (model != null)
            {
                model.Quantity = quantity;
                _dbContext.Carts.Add(model);
                _dbContext.Entry(model).State = EntityState.Modified;
            }

            return _dbContext.SaveChanges();
        }

        [HttpDelete]
        [Route("api/cart/deletecart")]
        public int DeleteCart(int id)
        {
            var model = _dbContext.Carts.Find(id);
            if (model != null) _dbContext.Carts.Remove(model);
            return _dbContext.SaveChanges();
        }

        [HttpDelete]
        [Route("api/cart/deleteallcart")]
        public int DeleteAll(string username)
        {
            _dbContext.Carts.RemoveRange(_dbContext.Carts.Where(x => x.Username == username));
            return _dbContext.SaveChanges();
        }

        [Route("api/cart/summary")]
        public int getSummary(string username)
        {
            var model = _dbContext.Carts.Where(x => x.Username == username);
            int sum = 0;
            foreach (var item in model)
            {
                sum += item.Quantity.Value * item.ProductDetail.Cost.Value;
            }

            return sum;
        }

        public int IsExist(int idProductDetails)
        {
            var model = _dbContext.Carts.FirstOrDefault(x => x.IDProductDetails == idProductDetails);
            if (model == null) return -1;
            return _dbContext.Carts.ToList().IndexOf(model);
        }
    }
}