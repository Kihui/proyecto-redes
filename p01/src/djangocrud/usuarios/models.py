from django.db import models
from django.contrib.auth.models import User
from django.db.models.signals import post_save
from django.dispatch import receiver
# Create your models here.

class Usuario(models.Model):
    
    #usuario = models.CharField(max_length=128, unique=True)
    usuario = models.OneToOneField(User, on_delete=models.CASCADE)

    def delete(self):
        if self.usuario:
            self.usuario.delete()
'''
@receiver(post_save, sender=User)
def update_user_profile(sender, instance, created, **kwargs):
    if created:
        Usuario.objects.create(usuario=instance)
    print("OKV!!")
    if not isinstance(instance, User):
        instance.usuario.save()
'''
