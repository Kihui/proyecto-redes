from django.forms import ModelForm, PasswordInput, RadioSelect
from .models import Usuario
from django.utils.translation import ugettext_lazy as _

class UsuarioForm(ModelForm):
    class Meta:
        model = Usuario
        fields = '__all__'
        
        labels = {
            'app': _('Apellido paterno'),
            'apm': _('Apellido materno'),
            'fecha_nac': _('Fecha nacimiento'),
        }

        widgets = {
            'passw': PasswordInput(),
            'sexo': RadioSelect(),
        }
