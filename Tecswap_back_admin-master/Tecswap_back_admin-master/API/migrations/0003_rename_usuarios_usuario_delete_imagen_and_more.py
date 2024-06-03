# Generated by Django 5.0.6 on 2024-06-01 08:50

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('API', '0002_remove_producto_imagen'),
    ]

    operations = [
        migrations.RenameModel(
            old_name='usuarios',
            new_name='usuario',
        ),
        migrations.DeleteModel(
            name='imagen',
        ),
        migrations.AddField(
            model_name='producto',
            name='imagen',
            field=models.ImageField(null=True, upload_to='imagenes/', verbose_name='Imagen'),
        ),
    ]
