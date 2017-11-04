from django.db import models

# Create your models here.

BOOL_CHOICES = ((True, 'Mujer'), (False, 'Hombre'))

class Usuario(models.Model):
    usuario = models.CharField(max_length=128, unique=True)
    nombre = models.CharField(max_length=128)
    app = models.CharField(max_length=128)
    apm = models.CharField(max_length=128)
    correo = models.EmailField(max_length=128, unique=True)
    fecha_nac = models.DateField()
    edad = models.IntegerField()
    sexo = models.BooleanField(choices=BOOL_CHOICES)
    passw = models.CharField(max_length=256)
