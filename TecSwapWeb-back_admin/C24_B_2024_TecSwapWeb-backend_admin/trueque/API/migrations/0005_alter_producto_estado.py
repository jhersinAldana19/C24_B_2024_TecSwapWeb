# Generated by Django 5.0.6 on 2024-06-27 08:22

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('API', '0004_carrera_alter_usuario_carrera'),
    ]

    operations = [
        migrations.AlterField(
            model_name='producto',
            name='estado',
            field=models.CharField(choices=[('pendiente', 'Pendiente'), ('cancelado', 'Cancelado')], default='pendiente', max_length=20),
        ),
    ]
