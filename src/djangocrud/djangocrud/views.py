from django.core.urlresolvers import reverse
from django.shortcuts import redirect, render
from django.contrib.auth.decorators import login_required
from usuarios.models import Usuario

@login_required
def home(request):
    sujeto = Usuario.objects.get(usuario_id=request.user.pk)
    return redirect(reverse('usuarios:usuario', args=(sujeto.pk,)))


def index(request):
    return redirect(reverse('usuarios:usuarios'))
