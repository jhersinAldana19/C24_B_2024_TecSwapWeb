# Generated by Django 5.0.6 on 2024-06-01 09:06

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('API', '0003_rename_usuarios_usuario_delete_imagen_and_more'),
    ]

    operations = [
        migrations.AlterField(
            model_name='producto',
            name='estado',
            field=models.CharField(choices=[('pendiente', 'Pendiente'), ('reservado', 'Reservado'), ('cancelado', 'Cancelado')], default='pendiente', max_length=20),
        ),
    ]