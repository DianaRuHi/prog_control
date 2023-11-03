# Необходимо написать проект, содержащий функционал работы с заметками.
# Программа должна уметь:
# - создавать заметку 
# - сохранять её
# - читать список заметок 
# - редактировать заметку 
# - удалять заметку 
# - показать содержимое заметки 
# - делать выборку по дате
# Заметка должна содержать:
# - идентификатор
# - заголовок
# - тело заметки
# - дату/время создания или последнего изменения заметки 
# Сохранение заметок необходимо сделать в формате json или csv формат (разделение полей рекомендуется делать через точку с запятой)


import csv
from datetime import date
from datetime import datetime


print ("Это програма по работе с заметками. В ней доступны следующие действия:")
print ("1 - Создать заметку")
print ("2 - Показать содержимое заметки")
print ("3 - Изменить заметку")
print ("4 - Удалить заметку")
print ("5 - Вывести список заметок")
print ("6 - Сделать выборку заметок по дате")
print ("7 - Завершить программу")
act = input("Введите номер действия, которое хотите совершить: ")

while True:
    if act == '1':
        print("Создание заметки.")
        name_note = input("Заголовок: ")
        body_note = input("Содержимое: ")
        id_n = 1
        date_n = date.today()
        with open('task_note/note.csv', 'r', newline='') as file:
            reader = csv.reader(file, delimiter =';')
            for row in reader:
                id_n = id_n + 1
            file.close()
        note = [id_n, name_note, body_note, date_n]
        with open('task_note/note.csv', 'a', newline='') as file:
            writer = csv.writer(file, delimiter =';') 
            writer.writerow(note)
            file.close()
        act = input("Введите номер действия, которое хотите совершить: ")
    
    elif act == '2':
        id_note = input("Введите номер заметки содержимое которой хотите увидеть: ")
        flag = 0
        with open('task_note/note.csv', 'r', newline='',) as file:
            reader = csv.reader(file, delimiter =';')
            for row in reader:
                if id_note == row[0]:
                    flag = 1
                    print("Заголовок: ", row[1])
                    print("Содержание: ", row[2])
                    print("Дата создания: ", row[3])
            file.close()
        if flag == 0:
            print("Нет заметки с таким номером.")
        act = input("Введите номер действия, которое хотите совершить: ")
    
    elif act == '3':
        id_note = input("Введите номер заметки которую хотите изменить: ")
        flag = 0
        notes = []
        with open('task_note/note.csv', 'r', newline='') as file:
            reader = csv.reader(file, delimiter =';')
            for row in reader:
                notes.append(row)
            file.close()
        for note in notes:
            if id_note == note[0]:
                print("Старый заголовок: " + note[1])
                note[1] = input("Новый заголовок: ")
                print("Старое содержание: " + note[2])
                note[2] = input("Новое содержание: ")
                note[3] = date.today()
                flag = 1
        if flag == 0:
            print("Нет заметки с таким номером.")
        else:
            with open('task_note/note.csv', 'w', newline='') as file:
                writer = csv.writer(file, delimiter =';') 
                for note in notes:
                    writer.writerow(note)
                file.close()
        act = input("Введите номер действия, которое хотите совершить: ")
    
    elif act == '4':
        id_note = input("Введите номер заметки которую хотите удалить: ")
        flag = 0
        notes = []
        with open('task_note/note.csv', 'r', newline='') as file:
            reader = csv.reader(file, delimiter =';')
            id_new = 0
            for row in reader:
                if id_note != row[0]:
                    id_new = id_new + 1
                    row[0] = id_new
                    notes.append(row)
                else:
                    flag = 1
            file.close()
        if flag == 0:
            print("Нет заметки с таким номером.")
        else:
            with open('task_note/note.csv', 'w', newline='') as file:
                writer = csv.writer(file, delimiter =';') 
                for note in notes:
                    writer.writerow(note)
                file.close()
        act = input("Введите номер действия, которое хотите совершить: ")
   
    elif act == '5':
        print("Список всех заметок: ")
        with open('task_note/note.csv', 'r', newline='') as file:
            reader = csv.reader(file, delimiter =';')
            for row in reader:
                print("id: " + row[0] + ". Заголовок: " + row[1])
            file.close()
        act = input("Введите номер действия, которое хотите совершить: ")
    
    elif act == '6':
        flag = 1
        while flag:
            date_from = input("Введите дату в формате год-месяц-день начиная с которой хотете увидеть заметки: ")
            try:
                date_f = datetime.strptime(date_from, '%Y-%m-%d')
                flag = 0
            except:
                print("Неверный формат даты.")
        flag = 1
        while flag:
            date_to = input("Введите дату в формате год-месяц-день до которой хотете увидеть заметки: ")
            try:
                date_t = datetime.strptime(date_to, '%Y-%m-%d')
                flag = 0
            except:
                print("Неверный формат даты.")
        print("Заметки созданные между " + date_from + " и " + date_to + " : ")
        with open('task_note/note.csv', 'r', newline='') as file:
            reader = csv.reader(file, delimiter =';')
            for row in reader:
                date_note = datetime.strptime(row[3], "%Y-%m-%d")
                if  date_f <= date_note <= date_t:
                    print("id: " + row[0] + ". Заголовок: " + row[1] + ". Дата: " + row[3])
        act = input("Введите номер действия, которое хотите совершить: ")
    
    elif act == '7':
        print("Завершение работы программы.")
        break
    else:
        print("Неверныый ввод, попробуйте еще раз.")
        print ("1 - Создать заметку")
        print ("2 - Показать содержимое заметки")
        print ("3 - Изменить заметку")
        print ("4 - Удалить заметку")
        print ("5 - Вывести список заметок")
        print ("6 - Сделать выборку заметок по дате")
        print ("7 - Завершить программу")       
        act = input("Введите номер действия, которое хотите совершить: ")
        




