# -*- coding: utf-8 -*-
# Generated by Django 1.9.7 on 2016-11-23 10:39
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('usuarios', '0001_initial'),
    ]

    operations = [
        migrations.AlterField(
            model_name='usuario',
            name='correo',
            field=models.EmailField(max_length=128, unique=True),
        ),
        migrations.AlterField(
            model_name='usuario',
            name='sexo',
            field=models.BooleanField(choices=[(True, 'Mujer'), (False, 'Hombre')], max_length=128),
        ),
        migrations.AlterField(
            model_name='usuario',
            name='usuario',
            field=models.CharField(max_length=128, unique=True),
        ),
    ]
