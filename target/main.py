from telebot import TeleBot, types
from rconadmin import RCONAdmin
from config import *
from telebotadmin import Telebotadmin

bot = TeleBot(TOKEN)
mcr = RCONAdmin(SERVER_IP, RCON_PASSWORD)
adm = Telebotadmin(bot)
uservars = {}

def playersmenu(prefix):
    menu = types.InlineKeyboardMarkup()
    for player in mcr.playerlist():
        menu.add(types.InlineKeyboardButton(text=f"{player}", callback_data=f"{prefix}{player}"))
    return menu


def itemsmenu(items: dict, prefix):
    menu = types.InlineKeyboardMarkup()
    for item in items.keys():
        menu.add(types.InlineKeyboardButton(text=f"{items[item]}", callback_data=f"{prefix}{item}"))
    return menu


@bot.message_handler(commands=['start'])
def start_handler(message: types.Message):
    adm.adduserm(message)
    bot.send_message(message.chat.id,
                     f"Привет, я RCON бот городка Данилы. Вы зарегистрированы как {adm.getpermm(message)}.")


@bot.message_handler(commands=['playerlist'])
@adm.setaccess(2)
def playerlist_handler(message: types.Message):
    players = "\n".join(mcr.playerlist())
    bot.send_message(message.chat.id, f"Игроки на сервере:\n{players}")


@bot.message_handler(commands=['givedoc'])
@adm.setaccess(1)
def givedoc_selectuser(message: types.Message):
    bot.send_message(message.chat.id, "Выберите игрока", reply_markup=playersmenu("docp"))


@bot.callback_query_handler(func=lambda call: "docp" in call.data)
def givedoc_selectdoc(callback: types.CallbackQuery):
    player = callback.data[4:]
    uservars[callback.message.chat.id] = player
    bot.send_message(callback.message.chat.id, "Выберите документ", reply_markup=itemsmenu(DOCS, "doci"))


@bot.callback_query_handler(func=lambda call: "doci" in call.data)
def givedoc_gice(callback: types.CallbackQuery):
    player = uservars[callback.message.chat.id]
    document = callback.data[4:]
    mcr.givedoc(player, document)
    bot.send_message(callback.message.chat.id, f"Документ {DOCS[document]} выдан игроку {player}")


@bot.message_handler(commands=['giveitem'])
@adm.setaccess(1)
def giveitem_selectitem(message: types.Message):
    bot.send_message(message.chat.id, "Выберите предмет", reply_markup=itemsmenu(ARMOR, "itmi"))


@bot.callback_query_handler(func=lambda call: "itmi" in call.data)
def giveitem_selectplayer(callback: types.CallbackQuery):
    item = callback.data[4:]
    uservars[callback.message.chat.id] = item
    bot.send_message(callback.message.chat.id, "Выберите игрока", reply_markup=playersmenu("itmp"))


@bot.callback_query_handler(func=lambda call: "itmp" in call.data)
def giveitem_give(callback: types.CallbackQuery):
    item = uservars[callback.message.chat.id]
    player = callback.data[4:]
    mcr.giveitem(player, item)
    bot.send_message(callback.message.chat.id, f"Предмет {ARMOR[item]} выдан игроку {player}")


@bot.message_handler(commands=['giveweapon'])
@adm.setaccess(1)
def giveweapon_selectweapon(message: types.Message):
    bot.send_message(message.chat.id, "Выберите оружие", reply_markup=itemsmenu(WEAPONS, "wpni"))


@bot.callback_query_handler(func=lambda call: "wpni" in call.data)
def giveweapon_selectplayer(callback: types.CallbackQuery):
    weapon = callback.data[4:]
    uservars[callback.message.chat.id] = weapon
    bot.send_message(callback.message.chat.id, "Выберите игрока", reply_markup=playersmenu("wpnp"))


@bot.callback_query_handler(func=lambda call: "wpnp" in call.data)
def giveweapon_give(callback: types.CallbackQuery):
    weapon = uservars[callback.message.chat.id]
    player = callback.data[4:]
    mcr.giveweapon(player, weapon)
    bot.send_message(callback.message.chat.id, f"Оружие {WEAPONS[weapon]} выдано игроку {player}")


@bot.message_handler(commands=['incperm'])
@adm.setaccess(2)
def incperm(message: types.Message):
    adm.requestperminc(message)


@bot.message_handler(commands=["freeze"])
@adm.setaccess(1)
def freeze_selectplayer(message: types.Message):
    bot.send_message(message.chat.id, "Выберите игрока", reply_markup=playersmenu("frzp"))


@bot.callback_query_handler(func=lambda call: "frzp" in call.data)
def freeze_freeze(callback: types.CallbackQuery):
    player = callback.data[4:]
    mcr.freeze(player)
    bot.send_message(callback.message.chat.id, f"Игрок {player} заморожен")


@bot.message_handler(commands=["unfreeze"])
@adm.setaccess(1)
def unfreeze_selectplayer(message: types.Message):
    bot.send_message(message.chat.id, "Выберите игрока", reply_markup=playersmenu("unfp"))


@bot.callback_query_handler(func=lambda call: "unfp" in call.data)
@adm.setaccess(1)
def unfreeze_unfreeze(callback: types.CallbackQuery):
    player = callback.data[4:]
    mcr.unfreeze(player)
    bot.send_message(callback.message.chat.id, f"Игрок {player} разморожен")


@bot.message_handler(content_types=["text"])
@adm.setaccess(0)
def rcon_command(message: types.Message):
    resp = mcr.command(message.text)
    bot.send_message(message.chat.id, resp)


if __name__ == '__main__':
    bot.infinity_polling()
