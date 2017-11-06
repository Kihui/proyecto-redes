from django.forms import ModelForm, PasswordInput, RadioSelect
from .models import Usuario
from django.contrib.auth.forms import UserCreationForm
from django.utils.translation import ugettext_lazy as _
from django.contrib.auth.models import User
from django import forms

class UsuarioForm(UserCreationForm):
    class Meta:
        model = User
        fields = ['username', 'first_name', 'last_name','email']
        
        labels = {
            'username': _('Nombre de usuario'),
            'first_name': _('Nombre'),            
            'last_name': _('Apellido'),
            'password': _('Contraseña'),
            'email': _('Correo electrónico'),
        }

class EditForm(ModelForm):
    class Meta:
        model = User
        fields = ['username', 'first_name', 'last_name','email', 'password']
        
        labels = {
            'username': _('Nombre de usuario'),
            'first_name': _('Nombre'),            
            'last_name': _('Apellido'),
            'password': _('Contraseña'),
            'email': _('Correo electrónico'),
        }

    def clean(self):
        cleaned = super(EditForm, self).clean()
        if User.objects.get(username=cleaned['username']):
            print("lol")
            raise forms.ValidationError("Este nombre ya está siendo utilizado, bestia.")
        print("meh")
        
        return cleaned
