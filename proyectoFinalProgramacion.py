# Bienvenido al sistema de gestión CineMas-Loja
cartelera = [
    {"pelicula": "Avatar", "horario": "14:00", "sala": 1, "precio": 5.00},
    {"pelicula": "Avengers: Endgame", "horario": "16:00", "sala": 2, "precio": 6.00},
    {"pelicula": "Titanic", "horario": "18:00", "sala": 3, "precio": 7.00},
    {"pelicula": "Inception", "horario": "20:00", "sala": 4, "precio": 8.00},
    {"pelicula": "The Dark Knight", "horario": "22:00", "sala": 5, "precio": 9.00},
    {"pelicula": "Forrest Gump", "horario": "14:00", "sala": 6, "precio": 5.00},
    {"pelicula": "The Matrix", "horario": "16:00", "sala": 7, "precio": 6.00},
    {"pelicula": "Pulp Fiction", "horario": "18:00", "sala": 8, "precio": 7.00},
    {"pelicula": "The Lion King", "horario": "20:00", "sala": 9, "precio": 8.00},
    {"pelicula": "Frozen", "horario": "22:00", "sala": 10, "precio": 9.00}
]

registro_ventas = []

def crear_matriz_asientos():
    return [["O" for _ in range(10)] for _ in range(10)]

asientos_por_sala = {i + 1: crear_matriz_asientos() for i in range(10)}

def mostrar_cartelera():
    print("\nCartelera de Películas:")
    for idx, pelicula in enumerate(cartelera):
        print(f"{idx + 1}. {pelicula['pelicula']} - Horario: {pelicula['horario']} - Sala: {pelicula['sala']} - Precio: ${pelicula['precio']:.2f}")

def mostrar_asientos(sala):
    print("\nEstado de los asientos (O = Libre, X = Ocupado):")
    for fila in range(10):
        print(" ".join(asientos_por_sala[sala][fila]))

def facturar_boletos():
    mostrar_cartelera()
    seleccion = int(input("Seleccione el número de la película: ")) - 1
    if seleccion < 0 or seleccion >= len(cartelera):
        print("Selección inválida. Por favor, inténtelo de nuevo.")
        return

    pelicula_seleccionada = cartelera[seleccion]
    sala = pelicula_seleccionada['sala']

    print(f"Usted seleccionó: {pelicula_seleccionada['pelicula']} - Sala: {sala}")
    mostrar_asientos(sala)

    cantidad = int(input("¿Cuántos boletos desea comprar?: "))
    asientos_seleccionados = []

    for _ in range(cantidad):
        fila = int(input("Seleccione la fila (1-10): ")) - 1
        columna = int(input("Seleccione la columna (1-10): ")) - 1

        if asientos_por_sala[sala][fila][columna] == "X":
            print("Este asiento ya está ocupado. Por favor, elija otro.")
            continue

        asientos_por_sala[sala][fila][columna] = "X"
        asientos_seleccionados.append((fila + 1, columna + 1))

    total = cantidad * pelicula_seleccionada['precio']

    print("Factura:")
    print(f"Pelicula: {pelicula_seleccionada['pelicula']}")
    print(f"Cantidad de boletos: {cantidad}")
    print(f"Asientos seleccionados: {asientos_seleccionados}")
    print(f"Total a pagar: ${total:.2f}")

    registro_ventas.append({
        "pelicula": pelicula_seleccionada['pelicula'],
        "horario": pelicula_seleccionada['horario'],
        "cantidad_boletos": cantidad,
        "asientos": asientos_seleccionados,
        "total": total
    })

def venta_snacks():
    snacks = {
        "Palomitas": 2.50,
        "Gaseosa": 1.50,
        "Chocolate": 1.00
    }

    print("\nMenú de Snacks:")
    for idx, (snack, precio) in enumerate(snacks.items()):
        print(f"{idx + 1}. {snack} - Precio: ${precio:.2f}")

    seleccion = int(input("Seleccione el snack que desea comprar (número): ")) - 1
    cantidad = int(input("¿Cuántos desea comprar?: "))

    snack_seleccionado = list(snacks.keys())[seleccion]
    precio_snack = list(snacks.values())[seleccion]
    total_snacks = cantidad * precio_snack

    print("Factura:")
    print(f"Snack: {snack_seleccionado}")
    print(f"Cantidad: {cantidad}")
    print(f"Total a pagar: ${total_snacks:.2f}")

    registro_ventas.append({
        "snack": snack_seleccionado,
        "cantidad_snacks": cantidad,
        "total": total_snacks
    })

def aplicar_promociones(total):
    descuento = 0.10  
    dia_promocion = "Miércoles"

    from datetime import datetime
    dia_actual = datetime.now().strftime("%A")

    if dia_actual == dia_promocion:
        total *= (1 - descuento)
        print(f"¡Hoy es {dia_promocion}! Se ha aplicado un {int(descuento * 100)}% de descuento.")
    return total

def registrar_venta():
    print("\nRegistro de Ventas:")
    for venta in registro_ventas:
        print(venta)

def menu():
    while True:
        print("\nBienvenido al Sistema de Gestión CineMas-Loja")
        print("1. Ver Cartelera")
        print("2. Comprar Boletos")
        print("3. Comprar Snacks")
        print("4. Ver Registro de Ventas")
        print("5. Salir")

        opcion = input("Seleccione una opción (1-5): ")

        if opcion == "1":
            mostrar_cartelera()
        elif opcion == "2":
            facturar_boletos()
        elif opcion == "3":
            venta_snacks()
        elif opcion == "4":
            registrar_venta()
        elif opcion == "5":
            print("Saliendo del sistema. ¡Gracias por usar CineMas-Loja!")
            break
        else:
            print("Opción inválida. Por favor, elija una opción del 1 al 5.")
menu()