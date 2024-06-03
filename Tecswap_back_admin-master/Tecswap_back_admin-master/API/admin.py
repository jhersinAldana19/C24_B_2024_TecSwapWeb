from django.contrib import admin
from .models import usuario, roles, categoria, producto, oferta, favorito, transaccion, historial

admin.site.register(usuario)
admin.site.register(roles)
admin.site.register(categoria)
admin.site.register(producto)
admin.site.register(oferta)
admin.site.register(favorito)
admin.site.register(transaccion)
admin.site.register(historial)